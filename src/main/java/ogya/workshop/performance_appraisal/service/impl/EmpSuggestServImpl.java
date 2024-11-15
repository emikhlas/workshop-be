package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.empsuggest.EmpSuggestCreateDto;
import ogya.workshop.performance_appraisal.dto.empsuggest.EmpSuggestDto;
import ogya.workshop.performance_appraisal.entity.EmpSuggest;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.EmpSuggestRepo;
import ogya.workshop.performance_appraisal.repository.UserRepo;
import ogya.workshop.performance_appraisal.service.EmpSuggestServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmpSuggestServImpl implements EmpSuggestServ {
    private final Logger Log = LoggerFactory.getLogger(EmpSuggestServImpl.class);

    @Autowired
    private EmpSuggestRepo empSuggestRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<EmpSuggestDto> findAll() {
        Log.info("Start findAll in EmpSuggestServImpl");
        List<EmpSuggestDto> result = new ArrayList<>();
        List<EmpSuggest> response = empSuggestRepo.findAll();
        for (EmpSuggest empSuggest : response) {
            EmpSuggestDto empSuggestDto = EmpSuggestDto.fromEntity(empSuggest);
            result.add(empSuggestDto);
        }
        Log.info("End findAll in EmpSuggestServImpl");
        return result;
    }

    @Override
    public List<EmpSuggestDto> findByUserId(UUID userId) {
        Log.info("Start findByUserId in EmpSuggestServImpl");
        List<EmpSuggestDto> result = new ArrayList<>();
        List<EmpSuggest> response = empSuggestRepo.findByUserId(userId);
        for (EmpSuggest empSuggest : response) {
            EmpSuggestDto empSuggestDto = EmpSuggestDto.fromEntity(empSuggest);
            result.add(empSuggestDto);
        }
        Log.info("End findByUserId in EmpSuggestServImpl");
        return result;
    }

    @Override
    public EmpSuggestDto findById(UUID id) {
        Log.info("Start findById in EmpSuggestServImpl");
        EmpSuggest empSuggest = empSuggestRepo.findById(id).orElseThrow(() -> new RuntimeException("EmpSuggest not found"));
        Log.info("End findById in EmpSuggestServImpl");
        return EmpSuggestDto.fromEntity(empSuggest);
    }

    @Override
    public EmpSuggestDto save(EmpSuggestCreateDto empSuggestCreateDto) {
        Log.info("Start save in EmpSuggestServImpl");
        EmpSuggest empSuggest = EmpSuggestCreateDto.toEntity(empSuggestCreateDto);
        User user = userRepo.findById(empSuggestCreateDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        empSuggest.setUser(user);
        empSuggest.setCreatedAt(LocalDate.now());
        empSuggestRepo.save(empSuggest);
        Log.info("End save in EmpSuggestServImpl");
        return EmpSuggestDto.fromEntity(empSuggest);
    }

    @Override
    public EmpSuggestDto update(UUID id,EmpSuggestCreateDto empSuggestCreateDto) {
        Log.info("Start update in EmpSuggestServImpl");
        EmpSuggest curentEmpSuggest = empSuggestRepo.findById(id).orElseThrow(() -> new RuntimeException("EmpSuggest not found"));
        if(empSuggestCreateDto.getUserId() != null) curentEmpSuggest.setUser(userRepo.findById(empSuggestCreateDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found")));
        if(empSuggestCreateDto.getSuggestion() != null) curentEmpSuggest.setSuggestion(empSuggestCreateDto.getSuggestion());
        if(empSuggestCreateDto.getAssessmentYear() != null) curentEmpSuggest.setAssessmentYear(empSuggestCreateDto.getAssessmentYear());
        curentEmpSuggest.setUpdatedAt(LocalDate.now());
        empSuggestRepo.save(curentEmpSuggest);
        Log.info("End update in EmpSuggestServImpl");
        return EmpSuggestDto.fromEntity(curentEmpSuggest);
    }

    @Override
    public Boolean deleteById(UUID id) {
        Log.info("Start deleteById in EmpSuggestServImpl");
        findById(id);
        empSuggestRepo.deleteById(id);
        Log.info("End deleteById in EmpSuggestServImpl");
        return true;
    }
}
