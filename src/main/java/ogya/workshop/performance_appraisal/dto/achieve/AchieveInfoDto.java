package ogya.workshop.performance_appraisal.dto.achieve;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.groupachieve.GroupAchieveInfoDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.Achieve;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AchieveInfoDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("achievement_name")
    private String achievementName;
    @JsonProperty("group_achievement")
    private GroupAchieveInfoDto groupAchievement;

    public static AchieveInfoDto fromEntity(Achieve achieve) {
        return AchieveInfoDto.builder()
                .id(achieve.getId())
                .achievementName(achieve.getAchievementName())
                .groupAchievement(GroupAchieveInfoDto.fromEntity(achieve.getGroupAchieve()))
                .build();
    }
}
