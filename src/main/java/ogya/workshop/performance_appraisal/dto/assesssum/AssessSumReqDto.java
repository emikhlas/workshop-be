package ogya.workshop.performance_appraisal.dto.assesssum;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.AssessSum;
import ogya.workshop.performance_appraisal.entity.User;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AssessSumReqDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("year")
    private Integer year;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("status")
    private Integer status;

    public static AssessSumReqDto fromEntity(AssessSum assessSum) {
        AssessSumReqDto dto = new AssessSumReqDto();
        dto.setId(assessSum.getId());
        dto.setUserId(assessSum.getUser().getId());
        dto.setYear(assessSum.getYear());
        dto.setScore(assessSum.getScore());
        dto.setStatus(assessSum.getStatus());
        return dto;
    }

    public static AssessSum toEntity(AssessSumReqDto assessSumReqDto, User user) {
        return toEntity(assessSumReqDto, user, null, null, null, null);
    }

    public static AssessSum toEntity(
            AssessSumReqDto assessSumReqDto, User user,
            UUID createdBy, LocalDate createdAt, UUID updatedBy, LocalDate updatedAt)
    {
        AssessSum assessSum = new AssessSum();
        user.setId(assessSumReqDto.getUserId());
        assessSum.setId(assessSumReqDto.getId());
        assessSum.setUser(user);
        assessSum.setYear(assessSumReqDto.getYear());
        assessSum.setScore(assessSumReqDto.getScore());
        assessSum.setStatus(assessSumReqDto.getStatus());
        assessSum.setCreatedAt(createdAt);
        assessSum.setCreatedBy(createdBy);
        assessSum.setUpdatedAt(updatedAt);
        assessSum.setUpdatedBy(updatedBy);
        return assessSum;
    }
}
