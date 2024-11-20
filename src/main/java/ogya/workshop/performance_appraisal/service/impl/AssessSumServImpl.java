package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumReqDto;
import ogya.workshop.performance_appraisal.dto.assesssum.AssessSumWithUserDto;
import ogya.workshop.performance_appraisal.entity.AssessSum;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.AssessSumRepo;
import ogya.workshop.performance_appraisal.repository.UserRepo;
import ogya.workshop.performance_appraisal.service.AssessSumServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AssessSumServImpl implements AssessSumServ {
    private final Logger Log = LoggerFactory.getLogger(AssessSumServImpl.class);
    @Autowired
    private AssessSumRepo assessSumRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<AssessSumWithUserDto> getAllAssessSum() {

        Log.info("Start getAllAssessSum in AssessSumServImpl");
        List<AssessSum> response = assessSumRepo.findAll();
        List<AssessSumWithUserDto> assessSumList = new ArrayList<>();
        for (AssessSum assessSum : response) {
            assessSumList.add(AssessSumWithUserDto.fromEntity(assessSum));
        }
        Log.info("End getAllAssessSum in AssessSumServImpl");

        return assessSumList;
    }

    @Override
    public List<AssessSumWithUserDto> getAssessSumByUserId(UUID userId) {
        Log.info("Start getAssessSumByUserId in AssessSumServImpl");
        List<AssessSum> response = assessSumRepo.findByUserId(userId);
        List<AssessSumWithUserDto> assessSumList = new ArrayList<>();
        for (AssessSum assessSum : response) {
            assessSumList.add(AssessSumWithUserDto.fromEntity(assessSum));
        }
        Log.info("End getAssessSumByUserId in AssessSumServImpl");
        return assessSumList;
    }

    @Override
    public AssessSumWithUserDto getAssessSumById(UUID id) {
        Log.info("Start getAssessSumById in AssessSumServImpl");
        AssessSum assessSum = assessSumRepo.findById(id).orElseThrow(() -> new RuntimeException("AssessSum not found"));
        Log.info("End getAssessSumById in AssessSumServImpl");
        return AssessSumWithUserDto.fromEntity(assessSum);
    }

    @Override
    public AssessSumWithUserDto createAssessSum(AssessSumReqDto assessSumReqDto) {
        Log.info("Start createAssessSum in AssessSumServImpl");
        User user = userRepo.findById(assessSumReqDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User  not found"));
        AssessSum assessSum = AssessSumReqDto.toEntity(assessSumReqDto);
        assessSum.setUser(user);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();
        assessSum.setCreatedBy(creator);
        assessSum.setCreatedAt(LocalDateTime.now());
        Log.info("End createAssessSum in AssessSumServImpl");
        return AssessSumWithUserDto.fromEntity(assessSumRepo.save(assessSum));
    }

    @Override
    public AssessSumWithUserDto updateAssessSum(UUID id, AssessSumReqDto assessSumReqDto) {
        Log.info("Start updateAssessSum in AssessSumServImpl");

        AssessSum assessSum = assessSumRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("AssessSum not found"));

        if (assessSumReqDto.getUserId() != null) {
            User user = userRepo.findById(assessSumReqDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User  not found"));
            assessSum.setUser(user);
        }

        if (assessSumReqDto.getYear() != null) {
            assessSum.setYear(assessSumReqDto.getYear());
        }

        if (assessSumReqDto.getScore() != null) {
            assessSum.setScore(assessSumReqDto.getScore());
        }

        if (assessSumReqDto.getStatus() != null) {
            assessSum.setStatus(assessSumReqDto.getStatus());
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();
        assessSum.setUpdatedBy(creator);
        assessSum.setUpdatedAt(LocalDateTime.now());

        Log.info("End updateAssessSum in AssessSumServImpl");
        return AssessSumWithUserDto.fromEntity(assessSumRepo.save(assessSum));
    }
    @Override
    public Boolean deleteAssessSum(UUID id) {
        Log.info("Start deleteAssessSum in AssessSumServImpl");
        if (assessSumRepo.existsById(id)) {
            assessSumRepo.deleteById(id);
            Log.info("End deleteAssessSum in AssessSumServImpl");
            return true;
        }
        throw new RuntimeException("AssessSum not found");
    }
}
