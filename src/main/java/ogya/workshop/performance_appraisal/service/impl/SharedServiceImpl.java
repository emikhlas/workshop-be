package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumDto;
import ogya.workshop.performance_appraisal.entity.AssessSum;
import ogya.workshop.performance_appraisal.repository.AssessSumRepo;
import ogya.workshop.performance_appraisal.service.AssessSumServ;
import ogya.workshop.performance_appraisal.service.SharedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SharedServiceImpl implements SharedService {

    private final AssessSumServ assessSumServ;
    @Autowired
    private AssessSumRepo assessSumRepo;

    @Autowired
    public SharedServiceImpl(@Lazy AssessSumServ assessSumServ) {
        this.assessSumServ = assessSumServ;
    }

    @Override
    public void updateAllAssessSums() {

        List<AssessSum> allAssessSums = assessSumRepo.findAll();

        List<AssessSumDto> updatedAssessSums = new ArrayList<>();

        for (AssessSum existingSum : allAssessSums) {
            AssessSumDto updatedSummary = assessSumServ.generateAssessSum(existingSum.getUser().getId(), existingSum.getYear());
            updatedAssessSums.add(updatedSummary);
        }
    }

    @Override
    public void generateAssessSum(UUID userId, Integer year) {
        assessSumServ.generateAssessSum(userId, year);
    }
}
