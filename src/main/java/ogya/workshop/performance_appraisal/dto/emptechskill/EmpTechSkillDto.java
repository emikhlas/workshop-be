package ogya.workshop.performance_appraisal.dto.emptechskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.techskill.TechSkillDto;
import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.entity.EmpTechSkill;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpTechSkillDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("user")
    private UserDto user;
    @JsonProperty("tech_skill")
    private TechSkillDto techSkill;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;
    @JsonProperty("created_by")
    private UserDto createdBy;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_by")
    private UserDto updatedBy;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public static EmpTechSkillDto fromEntity(EmpTechSkill empTechSkill) {
        EmpTechSkillDto dto = new EmpTechSkillDto();
        dto.setId(empTechSkill.getId());
        dto.setUser(UserDto.fromEntity(empTechSkill.getUser()));
        dto.setTechSkill(TechSkillDto.fromEntity(empTechSkill.getTechSkill()));
        dto.setScore(empTechSkill.getScore());
        dto.setAssessmentYear(empTechSkill.getAssessmentYear());
        dto.setCreatedAt(empTechSkill.getCreatedAt());
        dto.setUpdatedAt(empTechSkill.getUpdatedAt());
        return dto;
    }
}
