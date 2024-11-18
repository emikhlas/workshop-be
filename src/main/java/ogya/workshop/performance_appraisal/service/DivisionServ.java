package ogya.workshop.performance_appraisal.service;


import ogya.workshop.performance_appraisal.dto.DivisionDto;
import ogya.workshop.performance_appraisal.entity.Division;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DivisionServ {
    DivisionDto createDivision(DivisionDto divisionDto);
    DivisionDto updateDivision(UUID id, DivisionDto divisionDto);
    Optional<DivisionDto> getDivisionById(UUID id);
    List<DivisionDto> getAllDivision();
    boolean deleteDivision(UUID id);
}
