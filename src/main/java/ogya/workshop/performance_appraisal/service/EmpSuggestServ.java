package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.empsuggest.EmpSuggestCreateDto;
import ogya.workshop.performance_appraisal.dto.empsuggest.EmpSuggestDto;

import java.util.List;
import java.util.UUID;

public interface EmpSuggestServ {
    List<EmpSuggestDto> findAll();
    List<EmpSuggestDto> findByUserId(UUID userId);
    EmpSuggestDto findById(UUID id);
    EmpSuggestDto save(EmpSuggestCreateDto dto);
    EmpSuggestDto update(UUID id,EmpSuggestCreateDto dto);
    Boolean deleteById(UUID id);
}
