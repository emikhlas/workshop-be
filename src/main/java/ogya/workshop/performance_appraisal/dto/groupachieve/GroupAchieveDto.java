package ogya.workshop.performance_appraisal.dto.groupachieve;

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
public class GroupAchieveDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("group_achievement_name")
    private String groupAchievementName;
    @JsonProperty("percentage")
    private Integer percentage;
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
