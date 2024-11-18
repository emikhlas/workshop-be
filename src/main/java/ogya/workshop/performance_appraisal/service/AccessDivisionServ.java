package ogya.workshop.performance_appraisal.service;

import ogya.workshop.performance_appraisal.dto.AccessDivisionDto;
import ogya.workshop.performance_appraisal.dto.RoleMenuDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccessDivisionServ {
    AccessDivisionDto createAccessDivision(AccessDivisionDto accessDivisionDto);
    AccessDivisionDto updateAccessDivision(UUID id, AccessDivisionDto accessDivisionDto);
    Optional<AccessDivisionDto> getAccessDivisionById(UUID id);
    List<AccessDivisionDto> getAllAccessDivision();
    boolean deleteAccessDivision(UUID id);
}
