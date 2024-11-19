package ogya.workshop.performance_appraisal.dto.techskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.entity.TechSkill;

import java.time.LocalDate;
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
    private Boolean enabled;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("created_by")
    private UserDto createdBy;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("updated_by")
    private UserDto updatedBy;

    public static TechSkillDto fromEntity(TechSkill techSkill) {
        TechSkillDto dto = new TechSkillDto();
        dto.setId(techSkill.getId());
        dto.setTechSkill(techSkill.getTechSkill());
        dto.setEnabled(techSkill.getEnabled());
        dto.setCreatedAt(techSkill.getCreatedAt());
        dto.setUpdatedAt(techSkill.getUpdatedAt());
        return dto;
    }
}
