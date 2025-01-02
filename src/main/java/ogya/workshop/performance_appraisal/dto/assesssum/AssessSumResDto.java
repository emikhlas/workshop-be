package ogya.workshop.performance_appraisal.dto.assesssum;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.division.DivisionInfoDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AssessSumResDto {
    @JsonProperty("years")
    private List<Integer> years;
    @JsonProperty("assess_sums")
    private List<AssessSumDto> assessSumList;
}
