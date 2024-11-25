package ogya.workshop.performance_appraisal.dto.division;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.Division;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class DivisionInfoDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("division_name")
    private String divisionName;

    public static DivisionInfoDto fromEntity(Division division) {
        DivisionInfoDto dto = new DivisionInfoDto();
        dto.setId(division.getId());
        dto.setDivisionName(division.getDivisionName());
        return dto;
    }
}
