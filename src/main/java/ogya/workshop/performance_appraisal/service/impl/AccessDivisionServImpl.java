package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.accessdivision.AccessDivisionCreateDto;
import ogya.workshop.performance_appraisal.dto.accessdivision.AccessDivisionDto;
import ogya.workshop.performance_appraisal.entity.AccessDivision;
import ogya.workshop.performance_appraisal.entity.Division;
import ogya.workshop.performance_appraisal.entity.User;
import ogya.workshop.performance_appraisal.repository.AccessDivisionRepo;
import ogya.workshop.performance_appraisal.service.AccessDivisionServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccessDivisionServImpl implements AccessDivisionServ {

    @Autowired
    private AccessDivisionRepo accessDivisionRepo;

    @Override
    public AccessDivisionDto createAccessDivision(AccessDivisionCreateDto accessDivisionDto) {
        AccessDivision accessDivision = convertToEntity(accessDivisionDto);
        AccessDivision savedAccessDivision = accessDivisionRepo.save(accessDivision);
        return convertToDto(savedAccessDivision);
    }

    @Override
    public AccessDivisionDto updateAccessDivision(UUID id, AccessDivisionCreateDto accessDivisionDto) {
        if (!accessDivisionRepo.existsById(id)) {
            throw new IllegalArgumentException("Group Achievement with this ID does not exist.");
        }

        AccessDivision accessDivision = convertToEntity(accessDivisionDto);
        accessDivision.setId(id);

        AccessDivision updatedAccessDivision = accessDivisionRepo.save(accessDivision);
        return convertToDto(updatedAccessDivision);
    }

    @Override
    public Optional<AccessDivisionDto> getAccessDivisionById(UUID id) {
        Optional<AccessDivision> accessDivision = accessDivisionRepo.findById(id);
        return accessDivision.map(this::convertToDto);
    }

    @Override
    public List<AccessDivisionDto> getAllAccessDivision() {
        List<AccessDivision> accessDivisions = accessDivisionRepo.findAll();
        return accessDivisions.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public boolean deleteAccessDivision(UUID id) {
        accessDivisionRepo.deleteById(id);
        return true;
    }

    private AccessDivisionDto convertToDto(AccessDivision accessDivision) {
        AccessDivisionDto accessDivisionDto = new AccessDivisionDto();
        accessDivisionDto.setId(accessDivision.getId());
        if (accessDivision.getUser() != null) {
            accessDivisionDto.setUserId(accessDivision.getUser().getId());
        }
        if (accessDivision.getDivision() != null) {
            accessDivisionDto.setDivisionId(accessDivision.getDivision().getId());
        }
        return accessDivisionDto;
    }

    private AccessDivision convertToEntity(AccessDivisionCreateDto accessDivisionDto) {
        AccessDivision accessDivision = new AccessDivision();

        User user = new User();
        user.setId(accessDivisionDto.getUserId());
        accessDivision.setUser(user);

        Division division = new Division();
        division.setId(accessDivisionDto.getDivisionId());
        accessDivision.setDivision(division);
        return accessDivision;
    }
}
