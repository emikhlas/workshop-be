package ogya.workshop.performance_appraisal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class RoleMenuDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("role_id")
    private UUID roleId;
    @JsonProperty("menu_id")
    private UUID menuId;
}
