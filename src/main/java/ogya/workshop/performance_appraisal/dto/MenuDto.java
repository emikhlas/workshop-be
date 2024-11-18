package ogya.workshop.performance_appraisal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class MenuDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("menu_name")
    private String menuName;
}
