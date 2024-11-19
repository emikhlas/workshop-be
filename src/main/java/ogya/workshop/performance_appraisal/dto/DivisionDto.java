package ogya.workshop.performance_appraisal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class DivisionDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("division_name")
    private String divisionName;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("created_by")
    private UUID createdBy;
    @JsonProperty("updated_at")
    private Date updatedAt;
    @JsonProperty("updated_by")
    private UUID updatedBy;
}
