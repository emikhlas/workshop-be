package ogya.workshop.performance_appraisal.dto.empsuggest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.user.UserByDto;
import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.entity.EmpSuggest;
import ogya.workshop.performance_appraisal.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;
    @JsonProperty("created_by")
    private UserByDto createdBy;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("updated_by")
    private UserByDto updatedBy;

    public static EmpSuggestDto fromEntity(EmpSuggest empSuggest) {
        EmpSuggestDto empSuggestDto = new EmpSuggestDto();
        empSuggestDto.setId(empSuggest.getId());
        empSuggestDto.setUser(UserDto.fromEntity(empSuggest.getUser()));
        empSuggestDto.setSuggestion(empSuggest.getSuggestion());
        empSuggestDto.setAssessmentYear(empSuggest.getAssessmentYear());
        empSuggestDto.setCreatedAt(empSuggest.getCreatedAt());
        empSuggestDto.setUpdatedAt(empSuggest.getUpdatedAt());
        if(empSuggest.getCreatedBy() != null){
            empSuggestDto.setCreatedBy(UserByDto.fromEntity(empSuggest.getCreatedBy()));
        }
        if(empSuggest.getUpdatedBy() != null){
            empSuggestDto.setUpdatedBy(UserByDto.fromEntity(empSuggest.getUpdatedBy()));
        }
        return empSuggestDto;
    }
}
