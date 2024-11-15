package ogya.workshop.performance_appraisal.dto.userrole;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserRoleUpdateDto {
    @JsonProperty("role")
    private Set<UUID> role;
}
