package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.emptechskill.EmpTechSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.emptechskill.EmpTechSkillDto;
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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Override
    public List<EmpTechSkillDto> findAllByEmpId(UUID empId) {
        Log.info("Start findAllByEmpId in EmpTechSkillServImpl");
        List<EmpTechSkill> response = empTechSkillRepo.findByUserId(empId);
        Log.info("Received response: {}", response);
        List<EmpTechSkillDto> result = new ArrayList<>();
        for (EmpTechSkill empTechSkill : response) {
            EmpTechSkillDto empTechSkillDto = EmpTechSkillDto.fromEntity(empTechSkill);
            result.add(empTechSkillDto);
        }
        Log.info("End findAllByEmpId in EmpTechSkillServImpl");
        return result;
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

    @Override
    public EmpTechSkillDto save(EmpTechSkillCreateDto empTechSkillDto) {
        Log.info("Start save in EmpTechSkillServImpl");
        User user = userRepo.findById(empTechSkillDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        TechSkill techSkill = techSkillRepo.findById(empTechSkillDto.getTechSkillId()).orElseThrow(() -> new RuntimeException("TechSkill not found"));
        EmpTechSkill empTechSkill = EmpTechSkillCreateDto.toEntity(empTechSkillDto);
        empTechSkill.setUser(user);
        empTechSkill.setTechSkill(techSkill);
        empTechSkill.setCreatedAt(LocalDate.now());
        empTechSkillRepo.save(empTechSkill);
        Log.info("End save in EmpTechSkillServImpl");
        return EmpTechSkillDto.fromEntity(empTechSkill);
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
        empTechSkill.setUpdatedAt(LocalDate.now());
        empTechSkillRepo.save(empTechSkill);
        Log.info("End update in EmpTechSkillServImpl");
        return EmpTechSkillDto.fromEntity(empTechSkill);
    }
}
