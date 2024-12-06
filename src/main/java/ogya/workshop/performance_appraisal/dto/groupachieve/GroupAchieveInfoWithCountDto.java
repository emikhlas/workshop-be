package ogya.workshop.performance_appraisal.dto.groupachieve;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class GroupAchieveInfoWithCountDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("group_achievement_name")
    private String groupAchievementName;
    @JsonProperty("achievement_count")
    private Long achievementCount;
}
