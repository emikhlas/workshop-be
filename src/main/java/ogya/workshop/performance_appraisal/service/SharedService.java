package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumWithUserDto;

import java.util.List;

public interface SharedService {
    List<AssessSumWithUserDto> updateAllAssessSums();
}
