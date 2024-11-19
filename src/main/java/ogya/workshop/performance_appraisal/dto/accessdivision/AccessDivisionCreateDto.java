package ogya.workshop.performance_appraisal.dto.accessdivision;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AccessDivisionCreateDto {
    @JsonProperty("user_id")
    private UUID userId;
    @JsonProperty("division_id")
    private UUID divisionId;
}
