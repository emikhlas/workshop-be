package ogya.workshop.performance_appraisal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class GroupAttitudeSkillDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("percentage")
    private int percentage;
    @JsonProperty("enabled")
    private Integer enabled;
}
