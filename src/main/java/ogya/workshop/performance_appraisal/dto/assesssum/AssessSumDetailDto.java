package ogya.workshop.performance_appraisal.dto.assesssum;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ogya.workshop.performance_appraisal.dto.empachieveskill.EmpAchieveSkillDto;
import ogya.workshop.performance_appraisal.dto.empattitudeskill.EmpAttitudeSkillDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AssessSumDetailDto {
    @JsonProperty("assess_sum")
    private AssessSumDto assessSum;
    @JsonProperty("achieve_results")
    private List<GroupedResultDto<EmpAchieveSkillDto>> groupedAchieveResults;
    @JsonProperty("attitude_results")
    private List<GroupedResultDto<EmpAttitudeSkillDto>> groupedAttitudeResults;
}
