package ogya.workshop.performance_appraisal.dto.empsuggest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.entity.EmpSuggest;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpSuggestDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("user")
    private UserDto user;
    @JsonProperty("suggestion")
    private String suggestion;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;
    @JsonProperty("created_at")
    private LocalDate createdAt;
    @JsonProperty("created_by")
    private UUID createdBy;
    @JsonProperty("updated_at")
    private LocalDate updatedAt;
    @JsonProperty("updated_by")
    private UUID updatedBy;

    public static EmpSuggestDto fromEntity(EmpSuggest empSuggest) {
        EmpSuggestDto empSuggestDto = new EmpSuggestDto();
        empSuggestDto.setId(empSuggest.getId());
        empSuggestDto.setUser(UserDto.fromEntity(empSuggest.getUser()));
        empSuggestDto.setSuggestion(empSuggest.getSuggestion());
        empSuggestDto.setAssessmentYear(empSuggest.getAssessmentYear());
        empSuggestDto.setCreatedAt(empSuggest.getCreatedAt());
        empSuggestDto.setCreatedBy(empSuggest.getCreatedBy());
        empSuggestDto.setUpdatedAt(empSuggest.getUpdatedAt());
        empSuggestDto.setUpdatedBy(empSuggest.getUpdatedBy());
        return empSuggestDto;
    }
}
