package ogya.workshop.performance_appraisal.dto.division;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
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
    private UserInfoDto createdBy;
    @JsonProperty("updated_at")
    private Date updatedAt;
    @JsonProperty("updated_by")
    private UserInfoDto updatedBy;

    public static DivisionDto fromEntity(Division division) {
        DivisionDto dto = new DivisionDto();
        dto.setId(division.getId());
        dto.setDivisionName(division.getDivisionName());
        dto.setCreatedAt(division.getCreatedAt());
        if (division.getCreatedBy() != null) {
            dto.setCreatedBy(UserInfoDto.fromEntity(division.getCreatedBy()));
        }
        dto.setUpdatedAt(division.getUpdatedAt());
        if (division.getUpdatedBy() != null) {
            dto.setUpdatedBy(UserInfoDto.fromEntity(division.getUpdatedBy()));
        }
        return dto;
    }

    public static Division toEntity(DivisionDto division) {
        Division entity = new Division();
        entity.setId(division.getId());
        entity.setDivisionName(division.getDivisionName());
        entity.setCreatedAt(division.getCreatedAt());
        entity.setUpdatedAt(division.getUpdatedAt());

        return entity;
    }
}
