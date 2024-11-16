package ogya.workshop.performance_appraisal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpDevPlanDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("dev_plan_id")
    private UUID devPlanId;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;
}
