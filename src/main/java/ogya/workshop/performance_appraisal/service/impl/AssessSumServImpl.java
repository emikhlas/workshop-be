package ogya.workshop.performance_appraisal.service.impl;

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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        Optional<User> user = userRepo.findById(assessSumReqDto.getUserId());
        if(user.isPresent()){
            AssessSum assessSum = AssessSumReqDto.toEntity(assessSumReqDto, user.get(),UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6"), LocalDate.now() ,null ,null);
            return AssessSumWithUserDto.fromEntity(assessSumRepo.save(assessSum));
        } else {
            throw new RuntimeException("User not found");
        }

    }

    @Override
    public AssessSumWithUserDto updateAssessSum(UUID id, AssessSumReqDto assessSumReqDto) {
        Log.info("Start updateAssessSum in AssessSumServImpl");

        // Fetch the existing AssessSum
        AssessSum assessSum = assessSumRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("AssessSum not found"));

        // Create an instance for the updated AssessSum
        AssessSum updateAsses = new AssessSum();
        updateAsses.setId(id);
        updateAsses.setCreatedAt(assessSum.getCreatedAt());
        updateAsses.setCreatedBy(assessSum.getCreatedBy());
        updateAsses.setUpdatedBy(UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6")); // Ideally, get this from the current user context
        updateAsses.setUpdatedAt(LocalDate.now());

        // Update the user if a new user ID is provided
        if (assessSumReqDto.getId() != null) {
            User user = userRepo.findById(assessSumReqDto.getId())
                    .orElseThrow(() -> new RuntimeException("User  not found"));
            updateAsses.setUser (user);
        } else {
            updateAsses.setUser (assessSum.getUser ());
        }

        // Update year, status, and score if they are provided
        updateAsses.setYear(Optional.ofNullable(assessSumReqDto.getYear()).orElse(assessSum.getYear()));
        updateAsses.setStatus(Optional.ofNullable(assessSumReqDto.getStatus()).orElse(assessSum.getStatus()));
        updateAsses.setScore(Optional.ofNullable(assessSumReqDto.getScore()).orElse(assessSum.getScore()));

        // Save the updated AssessSum and return the DTO
        Log.info("End updateAssessSum in AssessSumServImpl");
        return AssessSumWithUserDto.fromEntity(assessSumRepo.save(updateAsses));
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
