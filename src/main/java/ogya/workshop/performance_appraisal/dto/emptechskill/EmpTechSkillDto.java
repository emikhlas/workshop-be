package ogya.workshop.performance_appraisal.dto.emptechskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.techskill.TechSkillDto;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.entity.EmpTechSkill;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class EmpTechSkillDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("user")
    private UserDto user;
    @JsonProperty("tech_skill")
    private TechSkillDto techSkill;
    @JsonProperty("tech_detail")
    private String techDetail;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;
    @JsonProperty("created_by")
    private UserInfoDto createdBy;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_by")
    private UserInfoDto updatedBy;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public static EmpTechSkillDto fromEntity(EmpTechSkill empTechSkill) {
        EmpTechSkillDto dto = new EmpTechSkillDto();
        dto.setId(empTechSkill.getId());
        dto.setUser(UserDto.fromEntity(empTechSkill.getUser()));
        dto.setTechSkill(TechSkillDto.fromEntity(empTechSkill.getTechSkill()));
        dto.setTechDetail(empTechSkill.getTechDetail());
        dto.setScore(empTechSkill.getScore());
        dto.setAssessmentYear(empTechSkill.getAssessmentYear());
        dto.setCreatedAt(empTechSkill.getCreatedAt());
        dto.setUpdatedAt(empTechSkill.getUpdatedAt());
        if(empTechSkill.getCreatedBy() != null){
            dto.setCreatedBy(UserInfoDto.fromEntity(empTechSkill.getCreatedBy()));
        }
        if(empTechSkill.getUpdatedBy() != null){
            dto.setUpdatedBy(UserInfoDto.fromEntity(empTechSkill.getUpdatedBy()));
        }
        return dto;
    }
}
