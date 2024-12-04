package ogya.workshop.performance_appraisal.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AuthRequestDto {
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}
