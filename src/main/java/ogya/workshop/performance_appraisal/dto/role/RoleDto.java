package ogya.workshop.performance_appraisal.dto.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;
import ogya.workshop.performance_appraisal.entity.Role;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class RoleDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("rolename")
    private String rolename;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("created_by")
    private UserInfoDto createdBy;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("updated_by")
    private UserInfoDto updatedBy;

    public static RoleDto fromEntity(Role role) {
        RoleDto dto = new RoleDto();
        dto.setId(role.getId());
        dto.setRolename(role.getRolename());
        dto.setCreatedAt(role.getCreatedAt());
        dto.setUpdatedAt(role.getUpdatedAt());
        if (role.getCreatedBy() != null) {
            dto.setCreatedBy(UserInfoDto.fromEntity(role.getCreatedBy()));
        }
        if (role.getUpdatedBy() != null) {
            dto.setUpdatedBy(UserInfoDto.fromEntity(role.getUpdatedBy()));
        }
        return dto;
    }
}
