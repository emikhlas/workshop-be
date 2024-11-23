package ogya.workshop.performance_appraisal.dto.achieve;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.user.UserByDto;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AchieveDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("achievement_name")
    private String achievementName;
    @JsonProperty("group_achievement_id")
    private UUID groupAchievementId;
    @JsonProperty("enabled")
    private Integer enabled;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("created_by")
    private UserByDto createdBy;
    @JsonProperty("updated_at")
    private Date updatedAt;
    @JsonProperty("updated_by")
    private UserByDto updatedBy;
}
