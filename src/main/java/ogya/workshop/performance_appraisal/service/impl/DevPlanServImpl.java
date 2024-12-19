package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.devplan.DevPlanCreateDto;
import ogya.workshop.performance_appraisal.dto.devplan.DevPlanDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.DevPlan;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.DevPlanRepo;
import ogya.workshop.performance_appraisal.service.DevPlanServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DevPlanServImpl implements DevPlanServ {

    @Autowired
    private DevPlanRepo devPlanRepo;

    @Override
    public DevPlanDto createDevPlan(DevPlanCreateDto devPlanDto) {
        DevPlan devPlan = convertToEntity(devPlanDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        devPlan.setCreatedBy(creator);

        devPlan.setCreatedAt(new Date());  // Set the creation date
        DevPlan savedDevPlan = devPlanRepo.save(devPlan);
        return convertToDto(savedDevPlan);
    }

    @Override
    public DevPlanDto updateDevPlan(UUID id, DevPlanCreateDto devPlanDto) {
        DevPlan currentDevPlan = devPlanRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("DevPlan with this ID does not exist."));

        if(devPlanDto.getPlan() != null){
            currentDevPlan.setPlan(devPlanDto.getPlan());
        }
        if(devPlanDto.getEnabled() != null){
            currentDevPlan.setEnabled(devPlanDto.getEnabled());
        }

        currentDevPlan.setUpdatedAt(new Date());  // Set the updated date

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        currentDevPlan.setUpdatedBy(creator);

        DevPlan updatedDevPlan = devPlanRepo.save(currentDevPlan);
        return convertToDto(updatedDevPlan);
    }

    @Override
    public Optional<DevPlanDto> getDevPlanById(UUID id) {
        Optional<DevPlan> devPlan = devPlanRepo.findById(id);
        return devPlan.map(this::convertToDto);
    }

    @Override
    public List<DevPlanDto> getAllDevPlan() {
        List<DevPlan> devPlan = devPlanRepo.findAll();
        return devPlan.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public boolean deleteDevPlan(UUID id) {
        devPlanRepo.deleteById(id);
        return true;
    }

    private DevPlanDto convertToDto(DevPlan devPlan) {
        DevPlanDto devPlanDto = new DevPlanDto();
        devPlanDto.setId(devPlan.getId());
        devPlanDto.setPlan(devPlan.getPlan());
        devPlanDto.setEnabled(devPlan.getEnabled());
        devPlanDto.setCreatedAt(devPlan.getCreatedAt());
        if(devPlan.getCreatedBy() != null){
            devPlanDto.setCreatedBy(UserInfoDto.fromEntity(devPlan.getCreatedBy()));
        }
        devPlanDto.setUpdatedAt(devPlan.getUpdatedAt());
        if(devPlan.getUpdatedBy() != null){
            devPlanDto.setUpdatedBy(UserInfoDto.fromEntity(devPlan.getUpdatedBy()));
        }
        return devPlanDto;
    }

    private DevPlan convertToEntity(DevPlanCreateDto devPlanDto) {
        DevPlan devPlan = new DevPlan();
        devPlan.setPlan(devPlanDto.getPlan());
        devPlan.setEnabled(devPlanDto.getEnabled());
        return devPlan;
    }

}
