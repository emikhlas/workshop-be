package ogya.workshop.performance_appraisal.dto.assesssum;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.AssessSum;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AssessSumReqDto {
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
        dto.setUserId(assessSum.getUser().getId());
        dto.setYear(assessSum.getYear());
        dto.setScore(assessSum.getScore());
        dto.setStatus(assessSum.getStatus());
        return dto;
    }

    public static AssessSum toEntity(
            AssessSumReqDto assessSumReqDto) {
        AssessSum assessSum = new AssessSum();
        assessSum.setYear(assessSumReqDto.getYear());
        assessSum.setScore(assessSumReqDto.getScore());
        assessSum.setStatus(assessSumReqDto.getStatus());
        return assessSum;
    }
}
