package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.division.DivisionCreateDto;
import ogya.workshop.performance_appraisal.dto.division.DivisionDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.Division;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.DivisionRepo;
import ogya.workshop.performance_appraisal.service.DivisionServ;
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
public class DivisionServImpl implements DivisionServ {

    @Autowired
    private DivisionRepo divisionRepo;

    @Override
    public DivisionDto createDivision(DivisionCreateDto divisionDto) {
        Division division = convertToEntity(divisionDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();
        division.setCreatedBy(creator);
        division.setCreatedAt(new Date());
        Division savedDivision = divisionRepo.save(division);
        return convertToDto(savedDivision);
    }

    @Override
    public DivisionDto updateDivision(UUID id, DivisionCreateDto divisionDto) {
        Division currentDivision = divisionRepo.findById(id).orElseThrow(() -> new RuntimeException("Division not found"));
        if (divisionDto.getDivisionName() != null) {
            currentDivision.setDivisionName(divisionDto.getDivisionName());
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();
        currentDivision.setUpdatedBy(creator);
        currentDivision.setUpdatedAt(new Date());

        Division updatedDivision = divisionRepo.save(currentDivision);
        return convertToDto(updatedDivision);
    }

    @Override
    public Optional<DivisionDto> getDivisionById(UUID id) {
        Optional<Division> division = divisionRepo.findById(id);
        return division.map(this::convertToDto);
    }

    @Override
    public List<DivisionDto> getAllDivision() {
        List<Division> divisions = divisionRepo.findAll();
        return divisions.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public boolean deleteDivision(UUID id) {
        divisionRepo.deleteById(id);
        return true;
    }

    private DivisionDto convertToDto(Division division) {
        DivisionDto divisionDto = new DivisionDto();
        divisionDto.setId(division.getId());
        divisionDto.setDivisionName(division.getDivisionName());
        divisionDto.setCreatedAt(division.getCreatedAt());
        if(division.getCreatedBy() != null) {
            divisionDto.setCreatedBy(UserInfoDto.fromEntity(division.getCreatedBy()));
        }
        divisionDto.setUpdatedAt(division.getUpdatedAt());
        if(division.getUpdatedBy() != null){
            divisionDto.setUpdatedBy(UserInfoDto.fromEntity(division.getUpdatedBy()));
        }
        return divisionDto;
    }

    private Division convertToEntity(DivisionCreateDto divisionDto) {
        Division division = new Division();
        division.setDivisionName(divisionDto.getDivisionName());
        return division;
    }
}
