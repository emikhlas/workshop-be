package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.techskill.TechSkillCreateDto;
import ogya.workshop.performance_appraisal.dto.techskill.TechSkillDto;

import java.util.List;
import java.util.UUID;

public interface TechSkillServ {
    List<TechSkillDto> findAll(boolean enabledOnly);
    TechSkillDto findById(UUID id);
    TechSkillDto save(TechSkillCreateDto dto);
    TechSkillDto update(UUID id, TechSkillCreateDto dto);
    Boolean deleteById(UUID id);
}
