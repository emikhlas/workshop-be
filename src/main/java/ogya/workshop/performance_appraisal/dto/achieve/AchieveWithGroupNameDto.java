package ogya.workshop.performance_appraisal.dto.achieve;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AchieveWithGroupNameDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("achievement_name")
    private String achievementName;
    @JsonProperty("group_achievement_name")
    private String groupAchievementName;
}
