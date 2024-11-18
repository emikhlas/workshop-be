package ogya.workshop.performance_appraisal.dto.emptechskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.EmpTechSkill;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpTechSkillCreateDto {
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("tech_skill_id")
    private UUID techSkillId;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;

    public static EmpTechSkill toEntity(EmpTechSkillCreateDto empTechSkillDto) {
        EmpTechSkill empTechSkill = new EmpTechSkill();
        empTechSkill.setScore(empTechSkillDto.getScore());
        empTechSkill.setAssessmentYear(empTechSkillDto.getAssessmentYear());
        return empTechSkill;
    }
}
