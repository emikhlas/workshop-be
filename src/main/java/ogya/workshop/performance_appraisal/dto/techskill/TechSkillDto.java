package ogya.workshop.performance_appraisal.dto.techskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.TechSkill;

import java.time.LocalDate;
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
    private Boolean enabled;
    @JsonProperty("created_at")
    private LocalDate createdAt;
    @JsonProperty("created_by")
    private UUID createdBy;
    @JsonProperty("updated_at")
    private LocalDate updatedAt;
    @JsonProperty("updated_by")
    private UUID updatedBy;

    public static TechSkillDto fromEntity(TechSkill techSkill) {
        TechSkillDto dto = new TechSkillDto();
        dto.setId(techSkill.getId());
        dto.setTechSkill(techSkill.getTechSkill());
        dto.setEnabled(techSkill.getEnabled());
        dto.setCreatedAt(techSkill.getCreatedAt());
        dto.setCreatedBy(techSkill.getCreatedBy());
        dto.setUpdatedAt(techSkill.getUpdatedAt());
        dto.setUpdatedBy(techSkill.getUpdatedBy());
        return dto;
    }
}
