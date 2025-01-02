package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumDetailDto;
import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumReqDto;

import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumDto;
import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumWithUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.UUID;

public interface AssessSumServ {

    List<AssessSumWithUserDto> getAllAssessSum();
    List<AssessSumWithUserDto> getAssessSumByUserId(UUID userId);
    AssessSumWithUserDto getAssessSumById(UUID id);
    AssessSumWithUserDto createAssessSum(AssessSumReqDto assessSumReqDto);
    AssessSumWithUserDto updateAssessSum(UUID id, AssessSumReqDto assessSumReqDto);
    Boolean deleteAssessSum(UUID id);
    AssessSumWithUserDto generateAssessSum(UUID userId, Integer year);
    AssessSumDetailDto getAssessSumDetail(UUID userId, Integer year);
    void generateAssessSumsForAllUsers(Integer year);

    List<AssessSumDto> getAllAssessSumByYear(Integer year);

    List<Integer> getDistinctAssessmentYears();

    List<AssessSumWithUserDto> getAllAssessSumWithUserByYear(Integer year);

    AssessSumWithUserDto getAssessmentSummary(UUID userId, Integer year);

    AssessSumWithUserDto updateAssessSumStatusToApprove(UUID id);

    AssessSumWithUserDto updateAssessSumStatusToUnapprove(UUID id);
}
