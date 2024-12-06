package ogya.workshop.performance_appraisal.dto.empattitudeskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpAttitudeSkillDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("attitude_skill_id")
    private UUID attitudeSkillId;
    @JsonProperty("attitude_skill_name")
    private String attitudeSkillName;
    @JsonProperty("score")
    private int score;
    @JsonProperty("assessment_year")
    private int assessmentYear;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("created_by")
    private UserInfoDto createdBy;
    @JsonProperty("updated_at")
    private Date updatedAt;
    @JsonProperty("updated_by")
    private UserInfoDto updatedBy;
}
