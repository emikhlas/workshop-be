package ogya.workshop.performance_appraisal.dto.empattitudeskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpAttitudeSkillUpdateRequestDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("attitude_skill_id")
    private UUID attitudeSkillId;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;
}
