package ogya.workshop.performance_appraisal.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.division.DivisionInfoDto;
import ogya.workshop.performance_appraisal.entity.User;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserInfoWithDivDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("email_address")
    private String emailAddress;
    @JsonProperty("position")
    private String position;
    @JsonProperty("division")
    private DivisionInfoDto division;

    public static UserInfoWithDivDto fromEntity(User user) {
        UserInfoWithDivDto userInfoDto = new UserInfoWithDivDto();
        userInfoDto.setId(user.getId());
        userInfoDto.setUsername(user.getUsername());
        userInfoDto.setFullName(user.getFullName());
        userInfoDto.setEmailAddress(user.getEmailAddress());
        userInfoDto.setPosition(user.getPosition());
        userInfoDto.setDivision(DivisionInfoDto.fromEntity(user.getDivision()));
        return userInfoDto;
    }

}
