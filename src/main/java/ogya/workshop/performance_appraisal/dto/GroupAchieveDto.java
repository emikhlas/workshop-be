package ogya.workshop.performance_appraisal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class GroupAchieveDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("group_achievement_name")
    private String groupAchievementName;
    @JsonProperty("enabled")
    private String enabled;
}
