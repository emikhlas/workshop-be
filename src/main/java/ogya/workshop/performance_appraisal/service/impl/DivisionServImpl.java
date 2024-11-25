package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.division.DivisionCreateDto;
import ogya.workshop.performance_appraisal.dto.division.DivisionDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.Division;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.DivisionRepo;
import ogya.workshop.performance_appraisal.service.DivisionServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DivisionServImpl implements DivisionServ {

    @Autowired
    private DivisionRepo divisionRepo;

    // Create a new Group Achieve
    @Override
    public DivisionDto createDivision(DivisionCreateDto divisionDto) {
        Division division = convertToEntity(divisionDto);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();
        division.setCreatedBy(creator);
        division.setCreatedAt(new Date());  // Set the creation date
        Division savedDivision = divisionRepo.save(division);
        return convertToDto(savedDivision);
    }

    // Update an existing Achieve
    @Override
    public DivisionDto updateDivision(UUID id, DivisionCreateDto divisionDto) {
        Division currentDivision = divisionRepo.findById(id).orElseThrow(() -> new RuntimeException("Division not found"));
        if (divisionDto.getDivisionName() != null) {
            currentDivision.setDivisionName(divisionDto.getDivisionName());
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        User creator = authUser.getUser();
        currentDivision.setUpdatedBy(creator);
        currentDivision.setUpdatedAt(new Date());  // Set the update date

        Division updatedDivision = divisionRepo.save(currentDivision);
        return convertToDto(updatedDivision);
    }

    // Retrieve by ID
    @Override
    public Optional<DivisionDto> getDivisionById(UUID id) {
        Optional<Division> division = divisionRepo.findById(id);
        return division.map(this::convertToDto);
    }

    // Retrieve all Achievements
    @Override
    public List<DivisionDto> getAllDivision() {
        List<Division> divisions = divisionRepo.findAll();
        return divisions.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Delete an Achieve by ID
    @Override
    public boolean deleteDivision(UUID id) {
        divisionRepo.deleteById(id);
        return true;
    }

    // Helper method to convert Achieve entity to AchieveDto
    private DivisionDto convertToDto(Division division) {
        DivisionDto divisionDto = new DivisionDto();
        divisionDto.setId(division.getId());
        divisionDto.setDivisionName(division.getDivisionName());
        divisionDto.setCreatedAt(division.getCreatedAt());
        if(division.getCreatedBy() != null) {
            divisionDto.setCreatedBy(UserInfoDto.fromEntity(division.getCreatedBy()));
        }
        divisionDto.setUpdatedAt(division.getUpdatedAt());
        if(division.getUpdatedBy() != null){
            divisionDto.setUpdatedBy(UserInfoDto.fromEntity(division.getUpdatedBy()));
        }
        return divisionDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private Division convertToEntity(DivisionCreateDto divisionDto) {
        Division division = new Division();
        division.setDivisionName(divisionDto.getDivisionName());
        return division;
    }

}
