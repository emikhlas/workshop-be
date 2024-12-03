package ogya.workshop.performance_appraisal.dto.rolemenu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class RoleMenuUpdateDto {
    @JsonProperty("menu")
    private Set<UUID> menu;
}
