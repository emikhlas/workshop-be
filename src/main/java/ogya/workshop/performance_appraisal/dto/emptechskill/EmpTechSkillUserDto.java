package ogya.workshop.performance_appraisal.dto.emptechskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpTechSkillUserDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("technical_skill_id")
    private UUID techSkillId;
    @JsonProperty("technical_skill")
    private String techSkill;
    @JsonProperty("tech_detail")
    private String techDetail;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;
    @JsonProperty("status")
    private String status;
    @JsonProperty("created_by")
    private UserInfoDto createdBy;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_by")
    private UserInfoDto updatedBy;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
