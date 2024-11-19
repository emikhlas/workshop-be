package ogya.workshop.performance_appraisal.dto.devplan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class DevPlanCreateDto {
    @JsonProperty("plan")
    private String plan;
    @JsonProperty("enabled")
    private Integer enabled;
}
