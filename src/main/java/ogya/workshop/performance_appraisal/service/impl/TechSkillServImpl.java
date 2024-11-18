package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.techskill.TechSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.techskill.TechSkillDto;
import ogya.workshop.performance_appraisal.entity.TechSkill;
import ogya.workshop.performance_appraisal.repository.TechSkillRepo;
import ogya.workshop.performance_appraisal.service.TechSkillServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TechSkillServImpl implements TechSkillServ {
    private final Logger Log = LoggerFactory.getLogger(TechSkillServImpl.class);

    @Autowired
    private TechSkillRepo techSkillRepo;

    @Override
    public List<TechSkillDto> findAll() {
        Log.info("Start findAll in TechSkillServImpl");
        List<TechSkillDto> result = new ArrayList<>();
        List<TechSkill> respomse = techSkillRepo.findAll();
        for (TechSkill techSkill : respomse) {
            TechSkillDto techSkillDto = TechSkillDto.fromEntity(techSkill);
            result.add(techSkillDto);
        }
        Log.info("End findAll in TechSkillServImpl");
        return result;
    }

    @Override
    public TechSkillDto findById(UUID id) {
        Log.info("Start findById in TechSkillServImpl");
        TechSkill techSkill = techSkillRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("TechSkill not found"));
        Log.info("End findById in TechSkillServImpl");
        return TechSkillDto.fromEntity(techSkill);
    }

    @Override
    public TechSkillDto save(TechSkillCreateDto dto) {
        Log.info("Start save in TechSkillServImpl");
        TechSkill techSkill = TechSkillCreateDto.toEntity(dto);
        techSkill.setCreatedAt(LocalDateTime.now());
        techSkillRepo.save(techSkill);
        Log.info("End save in TechSkillServImpl");
        return TechSkillDto.fromEntity(techSkill);
    }

    @Override
    public TechSkillDto update(UUID id, TechSkillCreateDto dto) {
        Log.info("Start update in TechSkillServImpl");
        TechSkill currentTechSkill = techSkillRepo.findById(id).orElseThrow(() -> new RuntimeException("TechSkill not found"));
        if(dto.getTechSkill() != null) currentTechSkill.setTechSkill(dto.getTechSkill());
        if(dto.getEnabled() != null) currentTechSkill.setEnabled(dto.getEnabled());
        currentTechSkill.setUpdatedAt(LocalDateTime.now());
        techSkillRepo.save(currentTechSkill);
        Log.info("End update in TechSkillServImpl");
        return TechSkillDto.fromEntity(currentTechSkill);
    }

    @Override
    public Boolean deleteById(UUID id) {
        Log.info("Start deleteById in TechSkillServImpl");
        findById(id);
        techSkillRepo.deleteById(id);
        Log.info("End deleteById in TechSkillServImpl");
        return true;
    }
}
