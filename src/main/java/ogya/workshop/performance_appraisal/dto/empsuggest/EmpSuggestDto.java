package ogya.workshop.performance_appraisal.dto.empsuggest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.EmpSuggest;

import java.time.LocalDateTime;
import java.util.Date;
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
    private UserInfoDto user;
    @JsonProperty("suggestion")
    private String suggestion;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("created_by")
    private UserInfoDto createdBy;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("updated_by")
    private UserInfoDto updatedBy;

    public static EmpSuggestDto fromEntity(EmpSuggest empSuggest) {
        EmpSuggestDto empSuggestDto = new EmpSuggestDto();
        empSuggestDto.setId(empSuggest.getId());
        empSuggestDto.setUser(UserInfoDto.fromEntity(empSuggest.getUser()));
        empSuggestDto.setSuggestion(empSuggest.getSuggestion());
        empSuggestDto.setAssessmentYear(empSuggest.getAssessmentYear());
        empSuggestDto.setCreatedAt(empSuggest.getCreatedAt());
        empSuggestDto.setUpdatedAt(empSuggest.getUpdatedAt());
        if(empSuggest.getCreatedBy() != null){
            empSuggestDto.setCreatedBy(UserInfoDto.fromEntity(empSuggest.getCreatedBy()));
        }
        if(empSuggest.getUpdatedBy() != null){
            empSuggestDto.setUpdatedBy(UserInfoDto.fromEntity(empSuggest.getUpdatedBy()));
        }
        return empSuggestDto;
    }
}
