package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.attitudeskill.AttitudeSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.attitudeskill.AttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.attitudeskill.AttitudeWithGroupNameDto;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillInfoDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.AttitudeSkill;
import ogya.workshop.performance_appraisal.entity.GroupAttitudeSkill;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.AttitudeSkillRepo;
import ogya.workshop.performance_appraisal.repository.GroupAttitudeSkillRepo;
import ogya.workshop.performance_appraisal.service.AttitudeSkillServ;
import ogya.workshop.performance_appraisal.service.SharedService;
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
public class AttitudeSkillServImpl implements AttitudeSkillServ {

    @Autowired
    private AttitudeSkillRepo attitudeSkillRepo;

    @Autowired
    private GroupAttitudeSkillRepo groupAttitudeSkillRepo;

    @Autowired
    private SharedService sharedService;

    @Override
    public AttitudeSkillDto createAttitudeSkill(AttitudeSkillCreateDto attitudeSkillDto) {
        AttitudeSkill attitudeSkill = convertToEntity(attitudeSkillDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        attitudeSkill.setCreatedBy(creator);
        attitudeSkill.setCreatedAt(new Date());

        AttitudeSkill savedAttitudeSkill = attitudeSkillRepo.save(attitudeSkill);
        sharedService.updateAllAssessSums();
        return convertToDto(savedAttitudeSkill);
    }

    @Override
    public AttitudeSkillDto updateAttitudeSkill(UUID id, AttitudeSkillCreateDto attitudeSkillDto) {
        AttitudeSkill currentAttitudeSkill = attitudeSkillRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Attitude Skill with this ID does not exist."));

        if (attitudeSkillDto.getAttitudeSkillName() != null) {
            currentAttitudeSkill.setAttitudeSkillName(attitudeSkillDto.getAttitudeSkillName());
        }

        if (attitudeSkillDto.getGroupAttitudeSkillId() != null) {

            GroupAttitudeSkill groupAttitudeSkill = groupAttitudeSkillRepo.findById(attitudeSkillDto.getGroupAttitudeSkillId()).orElseThrow(() -> new IllegalArgumentException("Group Attitude Skill with this ID does not exist."));
            currentAttitudeSkill.setGroupAttitudeSkill(groupAttitudeSkill);
        }
        if (attitudeSkillDto.getEnabled() != null) {
            currentAttitudeSkill.setEnabled(attitudeSkillDto.getEnabled());
        }

        currentAttitudeSkill.setUpdatedAt(new Date());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        currentAttitudeSkill.setUpdatedBy(creator);

        AttitudeSkill updatedAttitudeSkill = attitudeSkillRepo.save(currentAttitudeSkill);
        sharedService.updateAllAssessSums();
        return convertToDto(updatedAttitudeSkill);
    }

    @Override
    public Optional<AttitudeSkillDto> getAttitudeSkillById(UUID id) {
        Optional<AttitudeSkill> attitudeSkill = attitudeSkillRepo.findById(id);
        return attitudeSkill.map(this::convertToDto);
    }

    @Override
    public List<AttitudeSkillDto> getAllAttitudeSkills(boolean enabledOnly) {
        List<AttitudeSkill> response;
        if (enabledOnly) {
            response = attitudeSkillRepo.findAllByEnabled(1);
        } else {
            response = attitudeSkillRepo.findAll();
        }
        return response.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public boolean deleteAttitudeSkill(UUID id) {
        attitudeSkillRepo.deleteById(id);
        sharedService.updateAllAssessSums();
        return true;
    }

    @Override
    public List<AttitudeWithGroupNameDto> getAllAttitudeWithGroupName() {
        return attitudeSkillRepo.findAllWithGroupNames();
    }

    private AttitudeSkillDto convertToDto(AttitudeSkill attitudeSkill) {
        AttitudeSkillDto attitudeSkillDto = new AttitudeSkillDto();
        attitudeSkillDto.setId(attitudeSkill.getId());
        attitudeSkillDto.setAttitudeSkillName(attitudeSkill.getAttitudeSkillName());
        if (attitudeSkill.getGroupAttitudeSkill() != null) {
            attitudeSkillDto.setGroupAttitudeSkill(GroupAttitudeSkillInfoDto.fromEntity(attitudeSkill.getGroupAttitudeSkill()));
        }
        attitudeSkillDto.setEnabled(attitudeSkill.getEnabled());
        attitudeSkillDto.setCreatedAt(attitudeSkill.getCreatedAt());
        if (attitudeSkill.getCreatedBy() != null) {
            attitudeSkillDto.setCreatedBy(UserInfoDto.fromEntity(attitudeSkill.getCreatedBy()));
        }
        attitudeSkillDto.setUpdatedAt(attitudeSkill.getUpdatedAt());
        if (attitudeSkill.getUpdatedBy() != null) {
            attitudeSkillDto.setUpdatedBy(UserInfoDto.fromEntity(attitudeSkill.getUpdatedBy()));
        }
        return attitudeSkillDto;
    }

    private AttitudeSkill convertToEntity(AttitudeSkillCreateDto attitudeSkillDto) {
        AttitudeSkill attitudeSkill = new AttitudeSkill();
        attitudeSkill.setAttitudeSkillName(attitudeSkillDto.getAttitudeSkillName());
        if (attitudeSkillDto.getGroupAttitudeSkillId() != null) {
            GroupAttitudeSkill groupAttitudeSkill = new GroupAttitudeSkill();
            groupAttitudeSkill.setId(attitudeSkillDto.getGroupAttitudeSkillId());
            attitudeSkill.setGroupAttitudeSkill(groupAttitudeSkill);
        }
        attitudeSkill.setEnabled(attitudeSkillDto.getEnabled());
        return attitudeSkill;
    }

}
