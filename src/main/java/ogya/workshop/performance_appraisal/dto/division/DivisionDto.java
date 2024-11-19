package ogya.workshop.performance_appraisal.dto.division;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.Division;

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
        if(division.getCreatedBy() != null) {
            dto.setCreatedBy(division.getCreatedBy());
        }
        dto.setUpdatedAt(division.getUpdatedAt());
        if(division.getUpdatedBy() != null) {
            dto.setUpdatedBy(division.getUpdatedBy());
        }
        return dto;
    }

    public static Division toEntity(DivisionDto division) {
        Division entity = new Division();
        entity.setId(division.getId());
        entity.setDivisionName(division.getDivisionName());
        entity.setCreatedAt(division.getCreatedAt());
        if(division.getCreatedBy() != null) {
            entity.setCreatedBy(division.getCreatedBy());
        }
        entity.setUpdatedAt(division.getUpdatedAt());
        if(division.getUpdatedBy() != null) {
            entity.setUpdatedBy(division.getUpdatedBy());
        }
        return entity;
    }
}
