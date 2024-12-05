package ogya.workshop.performance_appraisal.dto.empachieveskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.achieve.AchieveInfoDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpAchieveSkillDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("user")
    private UserInfoDto user;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("achievement")
    private AchieveInfoDto achievement;
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
