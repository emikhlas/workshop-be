package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.AccessDivisionDto;
import ogya.workshop.performance_appraisal.dto.DivisionDto;
import ogya.workshop.performance_appraisal.entity.AccessDivision;
import ogya.workshop.performance_appraisal.entity.Division;
import ogya.workshop.performance_appraisal.repository.AccessDivisionRepo;
import ogya.workshop.performance_appraisal.service.AccessDivisionServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccessDivisionServImpl implements AccessDivisionServ {

    @Autowired
    private AccessDivisionRepo accessDivisionRepo;

    // Create a new Group Achieve
    @Override
    public AccessDivisionDto createAccessDivision(AccessDivisionDto accessDivisionDto) {
        AccessDivision accessDivision = convertToEntity(accessDivisionDto);
        AccessDivision savedAccessDivision = accessDivisionRepo.save(accessDivision);
        return convertToDto(savedAccessDivision);
    }

    // Update an existing Achieve
    @Override
    public AccessDivisionDto updateAccessDivision(UUID id, AccessDivisionDto accessDivisionDto) {
        if (!accessDivisionRepo.existsById(id)) {
            throw new IllegalArgumentException("Group Achievement with this ID does not exist.");
        }

        AccessDivision accessDivision = convertToEntity(accessDivisionDto);
        accessDivision.setId(id);  // Use the ID from the URL path

        AccessDivision updatedAccessDivision = accessDivisionRepo.save(accessDivision);
        return convertToDto(updatedAccessDivision);
    }

    // Retrieve by ID
    @Override
    public Optional<AccessDivisionDto> getAccessDivisionById(UUID id) {
        Optional<AccessDivision> accessDivision = accessDivisionRepo.findById(id);
        return accessDivision.map(this::convertToDto);
    }

    // Retrieve all Achievements
    @Override
    public List<AccessDivisionDto> getAllAccessDivision() {
        List<AccessDivision> accessDivisions = accessDivisionRepo.findAll();
        return accessDivisions.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Delete an Achieve by ID
    @Override
    public boolean deleteAccessDivision(UUID id) {
        accessDivisionRepo.deleteById(id);
        return true;
    }

    // Helper method to convert Achieve entity to AchieveDto
    private AccessDivisionDto convertToDto(AccessDivision accessDivision) {
        AccessDivisionDto accessDivisionDto = new AccessDivisionDto();
        accessDivisionDto.setId(accessDivision.getId());
        accessDivisionDto.setUserId(accessDivision.getUserId());
        accessDivisionDto.setDivisionId(accessDivision.getDivisionId());
        return accessDivisionDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private AccessDivision convertToEntity(AccessDivisionDto accessDivisionDto) {
        AccessDivision accessDivision = new AccessDivision();
        accessDivision.setId(accessDivisionDto.getId());
        accessDivision.setUserId(accessDivisionDto.getUserId());
        accessDivision.setDivisionId(accessDivisionDto.getDivisionId());
        return accessDivision;
    }

}
