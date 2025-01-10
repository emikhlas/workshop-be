package ogya.workshop.performance_appraisal.dto.techskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.TechSkill;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class TechSkillDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("technical_skill")
    private String techSkill;
    @JsonProperty("enabled")
    private Integer enabled;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("created_by")
    private UserInfoDto createdBy;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("updated_by")
    private UserInfoDto updatedBy;

    public static TechSkillDto fromEntity(TechSkill techSkill) {
        TechSkillDto dto = new TechSkillDto();
        dto.setId(techSkill.getId());
        dto.setTechSkill(techSkill.getTechSkill());
        dto.setEnabled(techSkill.getEnabled());
        dto.setCreatedAt(techSkill.getCreatedAt());
        dto.setUpdatedAt(techSkill.getUpdatedAt());
        if (techSkill.getCreatedBy() != null) {
            dto.setCreatedBy(UserInfoDto.fromEntity(techSkill.getCreatedBy()));
        }
        if (techSkill.getUpdatedBy() != null) {
            dto.setUpdatedBy(UserInfoDto.fromEntity(techSkill.getUpdatedBy()));
        }
        return dto;
    }
}
