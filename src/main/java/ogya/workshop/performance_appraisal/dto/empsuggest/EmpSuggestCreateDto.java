package ogya.workshop.performance_appraisal.dto.empsuggest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.EmpSuggest;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpSuggestCreateDto {
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("suggestion")
    private String suggestion;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;

    public static EmpSuggest toEntity(EmpSuggestCreateDto empDto) {
        EmpSuggest empSuggest = new EmpSuggest();
        empSuggest.setSuggestion(empDto.getSuggestion());
        empSuggest.setAssessmentYear(empDto.getAssessmentYear());
        return empSuggest;
    }
}
