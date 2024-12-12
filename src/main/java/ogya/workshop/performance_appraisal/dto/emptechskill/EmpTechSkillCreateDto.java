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
    @JsonProperty("tech_detail")
    private String techDetail;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;
    @JsonProperty("status")
    private String status;

    public static EmpTechSkill toEntity(EmpTechSkillCreateDto empTechSkillDto) {
        EmpTechSkill empTechSkill = new EmpTechSkill();
        empTechSkill.setTechDetail(empTechSkillDto.getTechDetail());
        empTechSkill.setScore(empTechSkillDto.getScore());
        empTechSkill.setAssessmentYear(empTechSkillDto.getAssessmentYear());
        empTechSkill.setStatus(empTechSkillDto.getStatus());
        return empTechSkill;
    }
}
