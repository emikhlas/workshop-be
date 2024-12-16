package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.empsuggest.EmpSuggestCreateDto;
import ogya.workshop.performance_appraisal.dto.empsuggest.EmpSuggestDto;

import java.util.List;
import java.util.UUID;

public interface EmpSuggestServ {
    List<EmpSuggestDto> findAll();
    List<EmpSuggestDto> findByUserId(UUID userId);
    EmpSuggestDto findById(UUID id);
    List<EmpSuggestDto> saveAll(List<EmpSuggestCreateDto> empSuggestCreateDtos);
    EmpSuggestDto update(UUID id,EmpSuggestCreateDto dto);
    Boolean deleteById(UUID id);
    List<EmpSuggestDto> findByUserIdAndAssessmentYear(UUID userId, Integer assessmentYear);
    List<Integer> getDistinctAssessmentYears();

}
