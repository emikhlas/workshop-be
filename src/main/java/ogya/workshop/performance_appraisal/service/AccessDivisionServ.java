package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.accessdivision.AccessDivisionCreateDto;
import ogya.workshop.performance_appraisal.dto.accessdivision.AccessDivisionDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccessDivisionServ {
    AccessDivisionDto createAccessDivision(AccessDivisionCreateDto accessDivisionDto);
    AccessDivisionDto updateAccessDivision(UUID id, AccessDivisionCreateDto accessDivisionDto);
    Optional<AccessDivisionDto> getAccessDivisionById(UUID id);
    List<AccessDivisionDto> getAllAccessDivision();
    boolean deleteAccessDivision(UUID id);
}
