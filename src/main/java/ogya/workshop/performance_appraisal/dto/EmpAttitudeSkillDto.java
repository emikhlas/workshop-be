package ogya.workshop.performance_appraisal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpAttitudeSkillDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("attitude_skill_id")
    private UUID attitudeSkillId;
    @JsonProperty("score")
    private int score;
    @JsonProperty("assessment_year")
    private int assessmentYear;
}
