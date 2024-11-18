package ogya.workshop.performance_appraisal.dto.achieve;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AchieveCreateDto {
    @JsonProperty("achievement_name")
    private String achievementName;
    @JsonProperty("group_achievement_id")
    private UUID groupAchievementId;
    @JsonProperty("enabled")
    private Integer enabled;
}
