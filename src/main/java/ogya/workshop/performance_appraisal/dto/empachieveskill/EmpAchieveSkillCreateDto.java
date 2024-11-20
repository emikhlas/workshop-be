package ogya.workshop.performance_appraisal.dto.empachieveskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpAchieveSkillCreateDto {
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("achievement_id")
    private UUID achievementId;
    @JsonProperty("score")
    private int score;
    @JsonProperty("assessment_year")
    private int assessmentYear;
}
