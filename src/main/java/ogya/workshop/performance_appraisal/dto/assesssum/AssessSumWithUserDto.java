package ogya.workshop.performance_appraisal.dto.assesssum;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.AssessSum;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AssessSumWithUserDto {
    @JsonProperty("id")
    private UUID id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonProperty("user")
    private UserInfoDto user;
    @JsonProperty("year")
    private int year;
    @JsonProperty("score")
    private int score;
    @JsonProperty("status")
    private int status;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public static AssessSumWithUserDto fromEntity(AssessSum assessSum) {
        AssessSumWithUserDto dto = new AssessSumWithUserDto();
        dto.setId(assessSum.getId());
        dto.setUser(UserInfoDto.fromEntity(assessSum.getUser()));
        dto.setYear(assessSum.getYear());
        dto.setScore(assessSum.getScore());
        dto.setStatus(assessSum.getStatus());
        dto.setCreatedAt(assessSum.getCreatedAt());
        dto.setUpdatedAt(assessSum.getUpdatedAt());
        return dto;
    }
}
