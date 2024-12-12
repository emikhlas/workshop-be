package ogya.workshop.performance_appraisal.dto.attitudeskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillInfoDto;
import ogya.workshop.performance_appraisal.entity.AttitudeSkill;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AttitudeSkillInfoDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("attitude_skill_name")
    private String attitudeSkillName;
    @JsonProperty("group_attitude_skill")
    private GroupAttitudeSkillInfoDto groupAttitudeSkill;
    @JsonProperty("enabled")
    private Integer enabled;

    public static AttitudeSkillInfoDto fromEntity(AttitudeSkill attitudeSkill) {
        return AttitudeSkillInfoDto.builder ().id (attitudeSkill.getId ())
                .attitudeSkillName (attitudeSkill.getAttitudeSkillName ())
                .groupAttitudeSkill (GroupAttitudeSkillInfoDto.fromEntity (attitudeSkill.getGroupAttitudeSkill ()))
                .enabled (attitudeSkill.getEnabled ())
                .build ();
    }
}
