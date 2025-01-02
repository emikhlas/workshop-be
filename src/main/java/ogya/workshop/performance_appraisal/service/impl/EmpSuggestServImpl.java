package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.attitudeskill.AttitudeSkillInfoDto;
import ogya.workshop.performance_appraisal.dto.empattitudeskill.EmpAttitudeSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.empattitudeskill.EmpAttitudeSkillDto;
import ogya.workshop.performance_appraisal.dto.empsuggest.EmpSuggestCreateDto;
import ogya.workshop.performance_appraisal.dto.empsuggest.EmpSuggestDto;
import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.AttitudeSkill;
import ogya.workshop.performance_appraisal.entity.EmpAttitudeSkill;
import ogya.workshop.performance_appraisal.entity.EmpSuggest;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.EmpSuggestRepo;
import ogya.workshop.performance_appraisal.repository.UserRepo;
import ogya.workshop.performance_appraisal.service.EmpSuggestServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EmpSuggestServImpl implements EmpSuggestServ {
    private final Logger Log = LoggerFactory.getLogger(EmpSuggestServImpl.class);

    @Autowired
    private EmpSuggestRepo empSuggestRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<EmpSuggestDto> findAll() {
        Log.info("Start findAll in EmpSuggestServImpl");
        List<EmpSuggestDto> result = new ArrayList<>();
        List<EmpSuggest> response = empSuggestRepo.findAll();
        for (EmpSuggest empSuggest : response) {
            EmpSuggestDto empSuggestDto = EmpSuggestDto.fromEntity(empSuggest);
            result.add(empSuggestDto);
        }
        Log.info("End findAll in EmpSuggestServImpl");
        return result;
    }

    @Override
    public List<EmpSuggestDto> findByUserId(UUID userId) {
        Log.info("Start findByUserId in EmpSuggestServImpl");
        List<EmpSuggestDto> result = new ArrayList<>();
        List<EmpSuggest> response = empSuggestRepo.findByUserId(userId);
        for (EmpSuggest empSuggest : response) {
            EmpSuggestDto empSuggestDto = EmpSuggestDto.fromEntity(empSuggest);
            result.add(empSuggestDto);
        }
        Log.info("End findByUserId in EmpSuggestServImpl");
        return result;
    }

    @Override
    public EmpSuggestDto findById(UUID id) {
        Log.info("Start findById in EmpSuggestServImpl");
        EmpSuggest empSuggest = empSuggestRepo.findById(id).orElseThrow(() -> new RuntimeException("EmpSuggest not found"));
        Log.info("End findById in EmpSuggestServImpl");
        return EmpSuggestDto.fromEntity(empSuggest);
    }

    @Override
    public List<EmpSuggestDto> saveAll(List<EmpSuggestCreateDto> empSuggestCreateDtos) {
        Log.info("Start saveAll in EmpSuggestServImpl");
        List<EmpSuggestDto> empSuggestDtos = new ArrayList<>();

        for (EmpSuggestCreateDto empSuggestCreateDto : empSuggestCreateDtos) {
            EmpSuggest empSuggest = EmpSuggestCreateDto.toEntity(empSuggestCreateDto);
            User user = userRepo.findById(empSuggestCreateDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User  not found for ID: " + empSuggestCreateDto.getUserId()));

            empSuggest.setUser (user);
            empSuggest.setCreatedAt(LocalDateTime.now());
            empSuggestRepo.save(empSuggest);

            empSuggestDtos.add(EmpSuggestDto.fromEntity(empSuggest));
        }

        Log.info("End saveAll in EmpSuggestServImpl");
        return empSuggestDtos;
    }

    @Override
    public EmpSuggestDto update(UUID id,EmpSuggestCreateDto empSuggestCreateDto) {
        Log.info("Start update in EmpSuggestServImpl");
        EmpSuggest curentEmpSuggest = empSuggestRepo.findById(id).orElseThrow(() -> new RuntimeException("EmpSuggest not found"));
        if(empSuggestCreateDto.getUserId() != null) curentEmpSuggest.setUser(userRepo.findById(empSuggestCreateDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        if(empSuggestCreateDto.getSuggestion() != null) curentEmpSuggest.setSuggestion(empSuggestCreateDto.getSuggestion());
        if(empSuggestCreateDto.getAssessmentYear() != null) curentEmpSuggest.setAssessmentYear(empSuggestCreateDto.getAssessmentYear());
        curentEmpSuggest.setUpdatedAt(LocalDateTime.now());
        empSuggestRepo.save(curentEmpSuggest);
        Log.info("End update in EmpSuggestServImpl");
        return EmpSuggestDto.fromEntity(curentEmpSuggest);
    }

    @Override
    public List<EmpSuggestDto> updates(List<UUID> ids, List<EmpSuggestCreateDto> empSuggestDtos) {
        if (ids.size() != empSuggestDtos.size()) {
            throw new IllegalArgumentException("The number of IDs must match the number of DTOs");
        }

        List<EmpSuggestDto> updatedDtos = new ArrayList<>();

        for (int i = 0; i < ids.size(); i++) {
            UUID id = ids.get(i);
            EmpSuggestCreateDto empSuggestDto = empSuggestDtos.get(i);

            EmpSuggest currentEmpSuggest = empSuggestRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("EmpSuggest not found for ID: " + id));

            if (empSuggestDto.getUserId() != null) {
                User user = new User();
                user.setId(empSuggestDto.getUserId());
                currentEmpSuggest.setUser (user);
            }

            currentEmpSuggest.setSuggestion(empSuggestDto.getSuggestion());
            currentEmpSuggest.setAssessmentYear(empSuggestDto.getAssessmentYear());

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            AuthUser  authUser  = (AuthUser ) authentication.getPrincipal();
            User creator = authUser .getUser ();

            currentEmpSuggest.setUpdatedBy(creator);
            currentEmpSuggest.setUpdatedAt(LocalDateTime.now());

            EmpSuggest updatedEmpSuggest = empSuggestRepo.save(currentEmpSuggest);
            updatedDtos.add(convertToDto(updatedEmpSuggest));
        }

        return updatedDtos;
    }


    @Override
    public List<EmpSuggestDto> findByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear) {
        Log.info("Start findByUserIdAndAssessmentYear in EmpSuggestServImpl");
        List<EmpSuggestDto> result = new ArrayList<>();
        List<EmpSuggest> response = empSuggestRepo.findByUserIdAndAssessmentYear(userId, assessmentYear);
        for (EmpSuggest empSuggest : response) {
            EmpSuggestDto empSuggestDto = EmpSuggestDto.fromEntity(empSuggest);
            result.add(empSuggestDto);
        }
        Log.info("End findByUserIdAndAssessmentYear in EmpSuggestServImpl");
        return result;
    }

    @Override
    public List<Integer> getDistinctAssessmentYears() {
        Log.info("Start getDistinctAssessmentYears in EmpSuggestServImpl");
        List<Integer> years = empSuggestRepo.findDistinctAssessmentYears();
        Log.info("End getDistinctAssessmentYears in EmpSuggestServImpl");
        return years;
    }


    @Override
    public Boolean deleteById(UUID id) {
        Log.info("Start deleteById in EmpSuggestServImpl");
        findById(id);
        empSuggestRepo.deleteById(id);
        Log.info("End deleteById in EmpSuggestServImpl");
        return true;
    }

    private EmpSuggestDto convertToDto(EmpSuggest empSuggest) {
        EmpSuggestDto empSuggestDto = new EmpSuggestDto();
        empSuggestDto.setId(empSuggest.getId());
        if (empSuggest.getUser() != null) {
            empSuggestDto.setUser(UserInfoDto.fromEntity(empSuggest.getUser()));
        }
        empSuggestDto.setSuggestion(empSuggest.getSuggestion());
        empSuggestDto.setAssessmentYear(empSuggest.getAssessmentYear());

        empSuggestDto.setCreatedAt(empSuggest.getCreatedAt());
        empSuggestDto.setUpdatedAt(empSuggest.getUpdatedAt());

        if (empSuggest.getCreatedBy() != null) {
            empSuggestDto.setCreatedBy(UserInfoDto.fromEntity(empSuggest.getCreatedBy()));
        }
        if (empSuggest.getUpdatedBy() != null) {
            empSuggestDto.setUpdatedBy(UserInfoDto.fromEntity(empSuggest.getUpdatedBy()));
        }

        return empSuggestDto;
    }
}
