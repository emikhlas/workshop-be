package ogya.workshop.performance_appraisal.dto.groupachieve;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.GroupAchieve;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class GroupAchieveInfoDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("group_achievement_name")
    private String groupAchievementName;
    @JsonProperty("percentage")
    private Integer percentage;
    @JsonProperty("enabled")
    private Integer enabled;

    public static GroupAchieveInfoDto fromEntity(GroupAchieve groupAchieve) {
        return GroupAchieveInfoDto.builder()
                .id(groupAchieve.getId())
                .groupAchievementName(groupAchieve.getGroupAchievementName())
                .percentage(groupAchieve.getPercentage())
                .enabled(groupAchieve.getEnabled())
                .build();
    }
}
