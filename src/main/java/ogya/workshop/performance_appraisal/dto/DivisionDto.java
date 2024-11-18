package ogya.workshop.performance_appraisal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.Division;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class DivisionDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("division_name")
    private String divisionName;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("created_by")
    private UUID createdBy;
    @JsonProperty("updated_at")
    private Date updatedAt;
    @JsonProperty("updated_by")
    private UUID updatedBy;

    public static DivisionDto fromEntity(Division division) {
        DivisionDto dto = new DivisionDto();
        dto.setId(division.getId());
        dto.setDivisionName(division.getDivisionName());
        dto.setCreatedAt(division.getCreatedAt());
        dto.setCreatedBy(division.getCreatedBy());
        dto.setUpdatedAt(division.getUpdatedAt());
        dto.setUpdatedBy(division.getUpdatedBy());
        return dto;
    }

    public static Division toEntity(DivisionDto division) {
        Division entity = new Division();
        entity.setId(division.getId());
        entity.setDivisionName(division.getDivisionName());
        entity.setCreatedAt(division.getCreatedAt());
        entity.setCreatedBy(division.getCreatedBy());
        entity.setUpdatedAt(division.getUpdatedAt());
        entity.setUpdatedBy(division.getUpdatedBy());
        return entity;
    }
}
