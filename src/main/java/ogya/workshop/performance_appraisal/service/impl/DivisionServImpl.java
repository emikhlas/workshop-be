package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.DevPlanDto;
import ogya.workshop.performance_appraisal.dto.DivisionDto;
import ogya.workshop.performance_appraisal.entity.DevPlan;
import ogya.workshop.performance_appraisal.entity.Division;
import ogya.workshop.performance_appraisal.repository.DivisionRepo;
import ogya.workshop.performance_appraisal.service.DivisionServ;
import org.springframework.beans.factory.annotation.Autowired;
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
    public DivisionDto createDivision(DivisionDto divisionDto) {
        Division division = convertToEntity(divisionDto);
        division.setCreatedAt(new Date());  // Set the creation date
        Division savedDivision = divisionRepo.save(division);
        return convertToDto(savedDivision);
    }

    // Update an existing Achieve
    @Override
    public DivisionDto updateDivision(UUID id, DivisionDto divisionDto) {
        if (!divisionRepo.existsById(id)) {
            throw new IllegalArgumentException("Group Achievement with this ID does not exist.");
        }

        Division division = convertToEntity(divisionDto);
        division.setId(id);  // Use the ID from the URL path
        division.setUpdatedAt(new Date());  // Set the updated date

        // Ensure 'createdAt' is set if it's null during the update
        if (division.getCreatedAt() == null) {
            division.setCreatedAt(new Date());  // Set current date if null
        }

        Division updatedDivision = divisionRepo.save(division);
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
        return divisionDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private Division convertToEntity(DivisionDto divisionDto) {
        Division division = new Division();
        division.setId(divisionDto.getId());
        division.setDivisionName(divisionDto.getDivisionName());
        return division;
    }

}
