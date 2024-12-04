package ogya.workshop.performance_appraisal.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class AuthResponseDto {
    @JsonProperty("user")
    private UserInfoDto user;
    @JsonProperty("token")
    private String token;
}
