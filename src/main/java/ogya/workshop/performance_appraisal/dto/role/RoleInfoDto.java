package ogya.workshop.performance_appraisal.dto.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.Role;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class RoleInfoDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("rolename")
    private String rolename;

    public static RoleInfoDto fromEntity(Role role) {
        RoleInfoDto dto = new RoleInfoDto();
        dto.setId(role.getId());
        dto.setRolename(role.getRolename());
        return dto;
    }
}
