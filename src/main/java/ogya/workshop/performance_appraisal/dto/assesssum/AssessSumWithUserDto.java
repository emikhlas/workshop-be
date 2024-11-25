package ogya.workshop.performance_appraisal.dto.assesssum;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.AssessSum;
import ogya.workshop.performance_appraisal.entity.User;

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
    private User user;
    @JsonProperty("year")
    private int year;
    @JsonProperty("score")
    private int score;
    @JsonProperty("status")
    private int status;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("created_by")
    private UserInfoDto createdBy;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("updated_by")
    private UserInfoDto updatedBy;

    public static AssessSumWithUserDto fromEntity(AssessSum assessSum) {
        AssessSumWithUserDto dto = new AssessSumWithUserDto();
        dto.setId(assessSum.getId());
        dto.setUser(assessSum.getUser());
        dto.setYear(assessSum.getYear());
        dto.setScore(assessSum.getScore());
        dto.setStatus(assessSum.getStatus());
        dto.setCreatedAt(assessSum.getCreatedAt());
        dto.setUpdatedAt(assessSum.getUpdatedAt());
        if(assessSum.getCreatedBy() != null) {
            dto.setCreatedBy(UserInfoDto.fromEntity(assessSum.getCreatedBy()));
        }
        if(assessSum.getUpdatedBy() != null) { 
            dto.setUpdatedBy(UserInfoDto.fromEntity(assessSum.getUpdatedBy()));
        }
        return dto;
    }

    public static AssessSum toEntity(AssessSumWithUserDto assessSumDto) {
        AssessSum assessSum = new AssessSum();
        assessSum.setId(assessSumDto.getId());
        assessSum.setUser(assessSumDto.getUser());
        assessSum.setYear(assessSumDto.getYear());
        assessSum.setScore(assessSumDto.getScore());
        assessSum.setStatus(assessSumDto.getStatus());
        assessSum.setCreatedAt(assessSumDto.getCreatedAt());
        assessSum.setUpdatedAt(assessSumDto.getUpdatedAt());
        return assessSum;
    }
}
