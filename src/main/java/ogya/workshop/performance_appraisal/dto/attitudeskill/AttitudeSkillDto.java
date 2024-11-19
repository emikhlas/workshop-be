package ogya.workshop.performance_appraisal.dto.attitudeskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AttitudeSkillDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("attitude_skill_name")
    private String attitudeSkillName;
    @JsonProperty("group_attitude_skill_id")
    private UUID groupAttitudeSkillId;
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
