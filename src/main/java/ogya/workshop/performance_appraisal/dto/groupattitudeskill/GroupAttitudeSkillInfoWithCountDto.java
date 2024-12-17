package ogya.workshop.performance_appraisal.dto.groupattitudeskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class GroupAttitudeSkillInfoWithCountDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("group_name")
    private String groupAttitudeName;
    @JsonProperty("percentage")
    private Integer percentage;
    @JsonProperty("enabled")
    private Integer enabled;
    @JsonProperty("attitude_count")
    private Integer attitudeCount;
}
