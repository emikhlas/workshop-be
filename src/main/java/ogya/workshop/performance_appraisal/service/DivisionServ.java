package ogya.workshop.performance_appraisal.service;


import ogya.workshop.performance_appraisal.dto.division.DivisionCreateDto;
import ogya.workshop.performance_appraisal.dto.division.DivisionDto;
import ogya.workshop.performance_appraisal.dto.division.DivisionInfoDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DivisionServ {
    DivisionDto createDivision(DivisionCreateDto divisionDto);

    DivisionDto updateDivision(UUID id, DivisionCreateDto divisionDto);

    Optional<DivisionDto> getDivisionById(UUID id);

    List<DivisionDto> getAllDivision();

    boolean deleteDivision(UUID id);

    List<DivisionInfoDto> getListDivisionName();
}
