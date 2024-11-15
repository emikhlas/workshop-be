package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumReqDto;
import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumWithUserDto;

import java.util.List;
import java.util.UUID;

public interface AssessSumServ {

    List<AssessSumWithUserDto> getAllAssessSum();
    List<AssessSumWithUserDto> getAssessSumByUserId(UUID userId);
    AssessSumWithUserDto getAssessSumById(UUID id);
    AssessSumWithUserDto createAssessSum(AssessSumReqDto assessSumReqDto);
    AssessSumWithUserDto updateAssessSum(UUID id, AssessSumReqDto assessSumReqDto);
    Boolean deleteAssessSum(UUID id);
}
