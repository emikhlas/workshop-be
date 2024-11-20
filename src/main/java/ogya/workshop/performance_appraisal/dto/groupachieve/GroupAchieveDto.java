package ogya.workshop.performance_appraisal.dto.groupachieve;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
    @JsonProperty("enabled")
    private Integer enabled;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("created_by")
    private UUID createdBy;
    @JsonProperty("updated_at")
    private Date updatedAt;
    @JsonProperty("updated_by")
    private UUID updatedBy;
}