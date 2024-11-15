package ogya.workshop.performance_appraisal.dto.userrole;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.role.RoleDto;
import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.entity.UserRole;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserRoleDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("role")
    private RoleDto role;
    @JsonProperty("user")
    private UserDto user;

    public static UserRoleDto fromEntity(UserRole userRole) {
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setId(userRole.getId());
        userRoleDto.setRole(RoleDto.fromEntity(userRole.getRole()));
        userRoleDto.setUser(UserDto.fromEntity(userRole.getUser()));
        return userRoleDto;
    }
}
