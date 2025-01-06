package ogya.workshop.performance_appraisal.service;

import java.util.UUID;

public interface SharedService {
    void updateAllAssessSums();
    void generateAssessSum(UUID userId, Integer year);
}
