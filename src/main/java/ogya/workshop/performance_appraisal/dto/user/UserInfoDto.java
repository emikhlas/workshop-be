package ogya.workshop.performance_appraisal.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.entity.User;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserInfoDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("email_address")
    private String emailAddress;

    public static UserInfoDto fromEntity(User createdBy) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(createdBy.getId());
        userInfoDto.setUsername(createdBy.getUsername());
        userInfoDto.setFullName(createdBy.getFullName());
        userInfoDto.setEmailAddress(createdBy.getEmailAddress());
        return userInfoDto;
    }

    public static UserInfoDto fromAuthenticatedUser(AuthUser authenticatedUser) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(authenticatedUser.getUser().getId());
        userInfoDto.setUsername(authenticatedUser.getUser().getUsername());
        userInfoDto.setFullName(authenticatedUser.getUser().getFullName());
        userInfoDto.setEmailAddress(authenticatedUser.getUser().getEmailAddress());
        return userInfoDto;
    }
}
