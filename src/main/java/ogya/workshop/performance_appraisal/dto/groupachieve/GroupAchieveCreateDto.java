package ogya.workshop.performance_appraisal.dto.groupachieve;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class GroupAchieveCreateDto {
    @JsonProperty("group_achievement_name")
    private String groupAchievementName;
    @JsonProperty("enabled")
    private Integer enabled;
}
