package ogya.workshop.performance_appraisal.dto.attitudeskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.AttitudeSkill;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AttitudeForEmpDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("attitude_skill_name")
    private String attitudeSkillName;

    public static AttitudeForEmpDto fromEntity(AttitudeSkill entity) {
        return AttitudeForEmpDto.builder()
                .id(entity.getId())
                .attitudeSkillName(entity.getAttitudeSkillName())
                .build();
    }
}
