package ogya.workshop.performance_appraisal.dto.empdevplan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpDevPlanCreateDto {
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("dev_plan_id")
    private UUID devPlanId;
    @JsonProperty("plan_detail")
    private String planDetail;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;
}
