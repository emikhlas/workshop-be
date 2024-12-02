package ogya.workshop.performance_appraisal.dto.rolemenu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.menu.MenuInfoDto;
import ogya.workshop.performance_appraisal.dto.role.RoleInfoDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class RoleMenuByRoleDto {
    @JsonProperty("role")
    RoleInfoDto role;
    @JsonProperty("menu")
    List<MenuInfoDto> menu;
}
