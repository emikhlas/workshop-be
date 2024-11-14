package ogya.workshop.performance_appraisal.dto.assesssum;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.AssessSum;
import ogya.workshop.performance_appraisal.entity.User;

import java.sql.Date;
import java.util.Optional;
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
    private Date createdAt;
    @JsonProperty("created_by")
    private UUID createdBy;
    @JsonProperty("updated_at")
    private Date updatedAt;
    @JsonProperty("updated_by")
    private UUID updatedBy;

    public static AssessSumWithUserDto fromEntity(AssessSum assessSum) {
        AssessSumWithUserDto dto = new AssessSumWithUserDto();
        dto.setId(assessSum.getId());
        dto.setUser(assessSum.getUser());
        dto.setYear(assessSum.getYear());
        dto.setScore(assessSum.getScore());
        dto.setStatus(assessSum.getStatus());
        dto.setCreatedAt(Optional.ofNullable(assessSum.getCreatedAt())
                .map(Date::valueOf)
                .orElse(null));
        dto.setCreatedBy(assessSum.getCreatedBy());
        dto.setUpdatedAt(Optional.ofNullable(assessSum.getUpdatedAt())
                .map(Date::valueOf)
                .orElse(null));
        dto.setUpdatedBy(assessSum.getUpdatedBy());
        return dto;
    }

    public static AssessSum toEntity(AssessSumWithUserDto assessSumDto) {
        AssessSum assessSum = new AssessSum();
        assessSum.setId(assessSumDto.getId());
        assessSum.setUser(assessSumDto.getUser());
        assessSum.setYear(assessSumDto.getYear());
        assessSum.setScore(assessSumDto.getScore());
        assessSum.setStatus(assessSumDto.getStatus());
        assessSum.setCreatedAt(assessSumDto.getCreatedAt().toLocalDate());
        assessSum.setCreatedBy(assessSumDto.getCreatedBy());
        assessSum.setUpdatedAt(assessSumDto.getUpdatedAt().toLocalDate());
        assessSum.setUpdatedBy(assessSumDto.getUpdatedBy());
        return assessSum;
    }
}
