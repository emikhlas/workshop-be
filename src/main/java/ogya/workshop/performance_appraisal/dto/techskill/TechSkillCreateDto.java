package ogya.workshop.performance_appraisal.dto.techskill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.TechSkill;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class TechSkillCreateDto {
    @JsonProperty("technical_skill")
    private String techSkill;
    @JsonProperty("enabled")
    private Integer enabled;

    public static TechSkill toEntity(TechSkillCreateDto dto) {
        TechSkill entity = new TechSkill();
        entity.setTechSkill(dto.getTechSkill());
        entity.setEnabled(dto.getEnabled());
        return entity;
    }
}
