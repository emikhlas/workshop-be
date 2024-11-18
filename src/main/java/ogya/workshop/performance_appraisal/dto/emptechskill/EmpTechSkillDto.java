package ogya.workshop.performance_appraisal.dto.emptechskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.techskill.TechSkillDto;
import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.entity.EmpTechSkill;
import ogya.workshop.performance_appraisal.entity.TechSkill;
import ogya.workshop.performance_appraisal.entity.User;

import java.time.LocalDate;
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
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("assessment_year")
    private Integer assessmentYear;
    @JsonProperty("created_by")
    private UUID createdBy;
    @JsonProperty("created_at")
    private LocalDate createdAt;
    @JsonProperty("updated_by")
    private UUID updatedBy;
    @JsonProperty("updated_at")
    private LocalDate updatedAt;

    public static EmpTechSkillDto fromEntity(EmpTechSkill empTechSkill) {
        EmpTechSkillDto dto = new EmpTechSkillDto();
        dto.setId(empTechSkill.getId());
        dto.setUser(UserDto.fromEntity(empTechSkill.getUser()));
        dto.setTechSkill(TechSkillDto.fromEntity(empTechSkill.getTechSkill()));
        dto.setScore(empTechSkill.getScore());
        dto.setAssessmentYear(empTechSkill.getAssessmentYear());
        dto.setCreatedBy(empTechSkill.getCreatedBy());
        dto.setCreatedAt(empTechSkill.getCreatedAt());
        dto.setUpdatedBy(empTechSkill.getUpdatedBy());
        dto.setUpdatedAt(empTechSkill.getUpdatedAt());
        return dto;
    }
}
