package ogya.workshop.performance_appraisal.dto.groupattitudeskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class GroupAttitudeSkillCreateDto {
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("percentage")
    private int percentage;
    @JsonProperty("enabled")
    private Integer enabled;
}
