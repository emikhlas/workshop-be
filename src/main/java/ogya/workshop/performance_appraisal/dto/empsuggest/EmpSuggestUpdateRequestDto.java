package ogya.workshop.performance_appraisal.dto.empsuggest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpSuggestUpdateRequestDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("suggestion")
    private String suggestion;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;
}
