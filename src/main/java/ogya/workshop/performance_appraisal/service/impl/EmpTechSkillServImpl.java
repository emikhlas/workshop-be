package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.empattitudeskill.EmpAttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.empdevplan.EmpDevPlanDto;
import ogya.workshop.performance_appraisal.dto.emptechskill.EmpTechSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.emptechskill.EmpTechSkillDto;
import ogya.workshop.performance_appraisal.dto.emptechskill.EmpTechSkillUserDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.EmpDevPlan;
import ogya.workshop.performance_appraisal.entity.EmpTechSkill;
import ogya.workshop.performance_appraisal.entity.TechSkill;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.EmpTechSkillRepo;
import ogya.workshop.performance_appraisal.repository.TechSkillRepo;
import ogya.workshop.performance_appraisal.repository.UserRepo;
import ogya.workshop.performance_appraisal.service.EmpTechSkillServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmpTechSkillServImpl implements EmpTechSkillServ {

    private final Logger Log = LoggerFactory.getLogger(EmpTechSkillServImpl.class);

    @Autowired
    private EmpTechSkillRepo empTechSkillRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TechSkillRepo techSkillRepo;

    @Override
    public List<EmpTechSkillDto> findAll() {
        Log.info("Start findAll in EmpTechSkillServImpl");
        List<EmpTechSkill> response = empTechSkillRepo.findAll();
        Log.info("Received response: {}", response);
        List<EmpTechSkillDto> result = new ArrayList<>();
        for (EmpTechSkill empTechSkill : response) {
            EmpTechSkillDto empTechSkillDto = EmpTechSkillDto.fromEntity(empTechSkill);
            result.add(empTechSkillDto);
        }
        Log.info("End findAll in EmpTechSkillServImpl");
        return result;
    }

//    @Override
//    public List<EmpTechSkillDto> findAllByEmpId(UUID empId) {
//        Log.info("Start findAllByEmpId in EmpTechSkillServImpl");
//        List<EmpTechSkill> response = empTechSkillRepo.findByUserId(empId);
//        Log.info("Received response: {}", response);
//        List<EmpTechSkillDto> result = new ArrayList<>();
//        for (EmpTechSkill empTechSkill : response) {
//            EmpTechSkillDto empTechSkillDto = EmpTechSkillDto.fromEntity(empTechSkill);
//            result.add(empTechSkillDto);
//        }
//        Log.info("End findAllByEmpId in EmpTechSkillServImpl");
//        return result;
//    }

    @Override
    public List<EmpTechSkillUserDto> findByUserId(UUID userId){
        List<EmpTechSkill> empTechSkills = empTechSkillRepo.findByUserId(userId);

        List<EmpTechSkillUserDto> empTechSkillUserDtos = empTechSkills.stream().map(empTechSkill -> {
            EmpTechSkillUserDto empTechSkillUserDto = convertToDto(empTechSkill);
            if (empTechSkill.getTechSkill() != null) {
                empTechSkillUserDto.setTechSkill(empTechSkill.getTechSkill().getTechSkill());  // Assuming DevPlan has a 'plan' attribute
            }
            return empTechSkillUserDto;
        }).collect(Collectors.toList());
        return empTechSkillUserDtos;
    }

    @Override
    public List<EmpTechSkillUserDto> findByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear) {
        List<EmpTechSkill> empTechSkills = empTechSkillRepo.findByUserIdAndAssessmentYear(userId, assessmentYear);

        List<EmpTechSkillUserDto> empTechSkillUserDtos = empTechSkills.stream().map(empTechSkill -> {
            EmpTechSkillUserDto empTechSkillUserDto = convertToDto(empTechSkill);
            if (empTechSkill.getTechSkill() != null) {
                empTechSkillUserDto.setTechSkill(empTechSkill.getTechSkill().getTechSkill());  // Assuming DevPlan has a 'plan' attribute
            }
            return empTechSkillUserDto;
        }).collect(Collectors.toList());
        return empTechSkillUserDtos;
    }

    @Override
    public List<Integer> getAllEmpTechSkillYears() {
        Log.info("Fetching all distinct assessment years from EmpTechSkill");
        List<Integer> assessmentYears = empTechSkillRepo.findDistinctAssessmentYears();
        Log.info("Found assessment years: {}", assessmentYears);
        return assessmentYears;
    }


    @Override
    public List<EmpTechSkillDto> findAllByTechSkillId(UUID techSkillId) {
        Log.info("Start findAllByTechSkillId in EmpTechSkillServImpl");
        List<EmpTechSkill> response = empTechSkillRepo.findByTechSkillId(techSkillId);
        Log.info("Received response: {}", response);
        List<EmpTechSkillDto> result = new ArrayList<>();
        for (EmpTechSkill empTechSkill : response) {
            EmpTechSkillDto empTechSkillDto = EmpTechSkillDto.fromEntity(empTechSkill);
            result.add(empTechSkillDto);
        }
        Log.info("End findAllByTechSkillId in EmpTechSkillServImpl");
        return result;
    }



//    @Override
//    public EmpTechSkillDto save(EmpTechSkillCreateDto empTechSkillDto) {
//        Log.info("Start save in EmpTechSkillServImpl");
//        User user = userRepo.findById(empTechSkillDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
//        TechSkill techSkill = techSkillRepo.findById(empTechSkillDto.getTechSkillId()).orElseThrow(() -> new RuntimeException("TechSkill not found"));
//        EmpTechSkill empTechSkill = EmpTechSkillCreateDto.toEntity(empTechSkillDto);
//        empTechSkill.setUser(user);
//        empTechSkill.setTechSkill(techSkill);
//        empTechSkill.setCreatedAt(LocalDateTime.now());
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        AuthUser authUser = (AuthUser) authentication.getPrincipal();
//        User creator = authUser.getUser();
//
//        empTechSkill.setCreatedBy(creator);
//        empTechSkillRepo.save(empTechSkill);
//        Log.info("End save in EmpTechSkillServImpl");
//        return EmpTechSkillDto.fromEntity(empTechSkill);
//    }

    @Override
    public List<EmpTechSkillDto> save(List<EmpTechSkillCreateDto> empTechSkillDtos) {
        Log.info("Start bulk save in EmpTechSkillServImpl");

        List<EmpTechSkill> empTechSkills = new ArrayList<>();
        List<EmpTechSkillDto> resultDtos = new ArrayList<>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        for (EmpTechSkillCreateDto dto : empTechSkillDtos) {
            User user = userRepo.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
            TechSkill techSkill = techSkillRepo.findById(dto.getTechSkillId()).orElseThrow(() -> new RuntimeException("TechSkill not found"));

            EmpTechSkill empTechSkill = EmpTechSkillCreateDto.toEntity(dto);
            empTechSkill.setUser(user);
            empTechSkill.setTechSkill(techSkill);
            empTechSkill.setCreatedAt(LocalDateTime.now());
            empTechSkill.setCreatedBy(creator);

            empTechSkills.add(empTechSkill);
        }

        empTechSkillRepo.saveAll(empTechSkills);  // Save all at once

        for (EmpTechSkill empTechSkill : empTechSkills) {
            resultDtos.add(EmpTechSkillDto.fromEntity(empTechSkill));
        }

        Log.info("End bulk save in EmpTechSkillServImpl");
        return resultDtos;
    }





    @Override
    public Boolean deleteById(UUID id) {
        Log.info("Start deleteById in EmpTechSkillServImpl");
        EmpTechSkill empTechSkill = empTechSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("EmpTechSkill not found"));
        empTechSkillRepo.deleteById(id);
        Log.info("End deleteById in EmpTechSkillServImpl");
        return true;
    }

    @Override
    public EmpTechSkillDto update(UUID id, EmpTechSkillCreateDto empTechSkillDto) {
        Log.info("Start update in EmpTechSkillServImpl");
        EmpTechSkill empTechSkill = empTechSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("EmpTechSkill not found"));
        if(empTechSkillDto.getUserId() != null) empTechSkill.setUser(userRepo.findById(empTechSkillDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        if(empTechSkillDto.getTechSkillId() != null) empTechSkill.setTechSkill(techSkillRepo.findById(empTechSkillDto.getTechSkillId()).orElseThrow(() -> new RuntimeException("TechSkill not found")));
        if(empTechSkillDto.getScore() != null) empTechSkill.setScore(empTechSkillDto.getScore());
        if(empTechSkillDto.getAssessmentYear() != null) empTechSkill.setAssessmentYear(empTechSkillDto.getAssessmentYear());
        empTechSkill.setUpdatedAt(LocalDateTime.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();

        empTechSkill.setUpdatedBy(creator);
        empTechSkillRepo.save(empTechSkill);
        Log.info("End update in EmpTechSkillServImpl");
        return EmpTechSkillDto.fromEntity(empTechSkill);
    }

    private EmpTechSkillUserDto convertToDto(EmpTechSkill empTechSkill) {
        EmpTechSkillUserDto empTechSkillUserDto = new EmpTechSkillUserDto();
        empTechSkillUserDto.setId(empTechSkill.getId());
        if (empTechSkill.getUser() !=null){
            empTechSkillUserDto.setUserId(empTechSkill.getUser().getId());
        }
        if (empTechSkill.getTechSkill() !=null){
            empTechSkillUserDto.setTechSkillId(empTechSkill.getTechSkill().getId());
        }
        empTechSkillUserDto.setTechDetail(empTechSkill.getTechDetail());
        empTechSkillUserDto.setScore(empTechSkill.getScore());
        empTechSkillUserDto.setAssessmentYear(empTechSkill.getAssessmentYear());
        empTechSkillUserDto.setStatus(empTechSkill.getStatus());
        empTechSkillUserDto.setCreatedAt(empTechSkill.getCreatedAt());
        empTechSkillUserDto.setUpdatedAt(empTechSkill.getUpdatedAt());
        if(empTechSkill.getCreatedBy() != null){
            empTechSkillUserDto.setCreatedBy(UserInfoDto.fromEntity(empTechSkill.getCreatedBy()));
        }
        if(empTechSkill.getUpdatedBy() != null){
            empTechSkillUserDto.setUpdatedBy(UserInfoDto.fromEntity(empTechSkill.getUpdatedBy()));
        }
        return empTechSkillUserDto;
    }
}
