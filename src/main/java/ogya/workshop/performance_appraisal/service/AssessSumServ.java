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

    Page<AssessSumDto> getFilteredAssessSum(String searchTerm, Integer year, List<UUID> divisionIds, Pageable pageable);
    List<AssessSumDto> getAllAssessSum();
    List<AssessSumDto> getAssessSumByUserId(UUID userId);
    AssessSumDto getAssessSumById(UUID id);
    AssessSumDto createAssessSum(AssessSumReqDto assessSumReqDto);
    AssessSumDto updateAssessSum(UUID id, AssessSumReqDto assessSumReqDto);
    Boolean deleteAssessSum(UUID id);
    AssessSumDto generateAssessSum(UUID userId, Integer year);
    AssessSumDetailDto getAssessSumDetail(UUID userId, Integer year);
    void generateAssessSumsForAllUsers(Integer year);
    List<AssessSumDto> getAllAssessSumByYear(Integer year);
    List<Integer> getDistinctAssessmentYears();
    List<AssessSumWithUserDto> getAllAssessSumWithUserByYear(Integer year);
    AssessSumWithUserDto getAssessmentSummary(UUID userId, Integer year);
    AssessSumWithUserDto updateAssessSumStatusToApprove(UUID id);
    AssessSumWithUserDto updateAssessSumStatusToUnapprove(UUID id);
}
