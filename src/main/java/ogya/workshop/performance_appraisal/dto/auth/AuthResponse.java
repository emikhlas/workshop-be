package ogya.workshop.performance_appraisal.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.user.UserDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AuthResponse {
    @JsonProperty("user")
    private UserDto user;
    @JsonProperty("token")
    private String token;
}
