package ogya.workshop.performance_appraisal.dto.division;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.Division;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class DivisionCreateDto {
    @JsonProperty("division_name")
    private String divisionName;

    public static DivisionCreateDto fromEntity(Division division) {
        DivisionCreateDto dto = new DivisionCreateDto();
        dto.setDivisionName(division.getDivisionName());
        return dto;
    }

    public static Division toEntity(DivisionCreateDto division) {
        Division entity = new Division();
        entity.setDivisionName(division.getDivisionName());
        return entity;
    }
}
