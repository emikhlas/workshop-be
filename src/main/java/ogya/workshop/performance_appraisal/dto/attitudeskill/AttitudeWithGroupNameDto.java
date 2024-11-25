package ogya.workshop.performance_appraisal.dto.attitudeskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AttitudeWithGroupNameDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("attitude_skill_name")
    private String attitudeSkillName;
    @JsonProperty("group_attitude_skill_id")
    private UUID groupAttitudeSkillId;
    @JsonProperty("group_name")
    private String groupName;
}
