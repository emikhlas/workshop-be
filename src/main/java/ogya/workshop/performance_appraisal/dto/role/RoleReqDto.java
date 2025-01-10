package ogya.workshop.performance_appraisal.dto.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.Role;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class RoleReqDto {
    @JsonProperty("rolename")
    private String rolename;


    public static Role toEntity(RoleReqDto roleDto) {
        Role role = new Role();
        role.setRolename(roleDto.getRolename().toUpperCase());
        return role;
    }
}
