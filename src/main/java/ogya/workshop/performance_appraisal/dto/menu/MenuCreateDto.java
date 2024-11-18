package ogya.workshop.performance_appraisal.dto.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class MenuCreateDto {
    @JsonProperty("menu_name")
    private String menuName;
}
