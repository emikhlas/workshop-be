package ogya.workshop.performance_appraisal.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class ChangePasswordDto {
    @JsonProperty("old_password")
    private String oldPassword;
    @JsonProperty("new_password")
    private String newPassword;
}
