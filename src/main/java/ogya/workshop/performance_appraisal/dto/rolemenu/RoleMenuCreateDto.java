package ogya.workshop.performance_appraisal.dto.rolemenu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class RoleMenuCreateDto {
    @JsonProperty("role_id")
    private UUID roleId;
    @JsonProperty("menu_id")
    private UUID menuId;
}
