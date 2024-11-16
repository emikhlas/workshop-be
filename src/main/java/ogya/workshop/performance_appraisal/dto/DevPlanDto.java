package ogya.workshop.performance_appraisal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class DevPlanDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("plan")
    private String plan;
    @JsonProperty("enabled")
    private Integer enabled;
}
