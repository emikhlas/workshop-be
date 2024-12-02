package ogya.workshop.performance_appraisal.dto.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class MenuInfoDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("menu_name")
    private String menuName;
}
