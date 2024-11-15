package ogya.workshop.performance_appraisal.dto.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.Role;
import org.springframework.beans.BeanUtils;

import java.beans.BeanProperty;
import java.time.LocalDate;
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
    private LocalDate createdAt;
    @JsonProperty("created_by")
    private UUID createdBy;
    @JsonProperty("updated_at")
    private LocalDate updatedAt;
    @JsonProperty("updated_by")
    private UUID updatedBy;

    public static RoleDto fromEntity(Role role) {
        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(role, roleDto);
        return roleDto;
    }
}
