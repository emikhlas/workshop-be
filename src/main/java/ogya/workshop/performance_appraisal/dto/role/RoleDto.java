package ogya.workshop.performance_appraisal.dto.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.user.UserDto;
import ogya.workshop.performance_appraisal.entity.Role;
import ogya.workshop.performance_appraisal.entity.User;
import org.springframework.beans.BeanUtils;

import java.beans.BeanProperty;
import java.time.LocalDate;
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
    private UserDto createdBy;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("updated_by")
    private UserDto updatedBy;

    public static RoleDto fromEntity(Role role) {
        RoleDto dto = new RoleDto();
        dto.setId(role.getId());
        dto.setRolename(role.getRolename());
        dto.setCreatedAt(role.getCreatedAt());
        dto.setUpdatedAt(role.getUpdatedAt());
        return dto;
    }
}
