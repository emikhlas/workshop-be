package ogya.workshop.performance_appraisal.dto.groupattitudeskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.GroupAttitudeSkill;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class GroupAttitudeSkillInfoDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("percentage")
    private Integer percentage;
    @JsonProperty("enabled")
    private Integer enabled;

    public static GroupAttitudeSkillInfoDto fromEntity(GroupAttitudeSkill groupAttitudeSkill) {
        return GroupAttitudeSkillInfoDto.builder()
                .id(groupAttitudeSkill.getId())
                .groupName(groupAttitudeSkill.getGroupName())
                .percentage(groupAttitudeSkill.getPercentage())
                .enabled(groupAttitudeSkill.getEnabled())
                .build();
    }
}
