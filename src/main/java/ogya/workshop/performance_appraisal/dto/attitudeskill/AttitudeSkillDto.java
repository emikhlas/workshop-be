package ogya.workshop.performance_appraisal.dto.attitudeskill;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.groupattitudeskill.GroupAttitudeSkillInfoDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AttitudeSkillDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("attitude_skill_name")
    private String attitudeSkillName;
    @JsonProperty("group_attitude_skill")
    private GroupAttitudeSkillInfoDto groupAttitudeSkill;
    @JsonProperty("enabled")
    private Integer enabled;
    @JsonFormat(timezone = "Asia/Jakarta")
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("created_by")
    private UserInfoDto createdBy;
    @JsonFormat(timezone = "Asia/Jakarta")
    @JsonProperty("updated_at")
    private Date updatedAt;
    @JsonProperty("updated_by")
    private UserInfoDto updatedBy;
}
