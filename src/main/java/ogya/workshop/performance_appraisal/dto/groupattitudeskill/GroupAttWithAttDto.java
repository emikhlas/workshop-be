package ogya.workshop.performance_appraisal.dto.groupattitudeskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.attitudeskill.AttitudeForEmpDto;
import ogya.workshop.performance_appraisal.entity.GroupAttitudeSkill;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class GroupAttWithAttDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("attitude_skills")
    private List<AttitudeForEmpDto> attitudeSkills;

    public static GroupAttWithAttDto fromEntity(GroupAttitudeSkill entity) {
        return GroupAttWithAttDto.builder()
                .id(entity.getId())
                .groupName(entity.getGroupName())
                .attitudeSkills(entity.getAttitudeSkills().stream()
                        .map(AttitudeForEmpDto::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
