package ogya.workshop.performance_appraisal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AchieveDto {
    @JsonProperty("id")
    private String id;
    @JsonProperty("achievement_name")
    private String achievementName;
    @JsonProperty("group_achievement_id")
    private String groupAchievementId;
    @JsonProperty("enabled")
    private String enabled;
}
