package ogya.workshop.performance_appraisal.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.User;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserByDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("email_address")
    private String emailAddress;

    public static UserByDto fromEntity(User createdBy) {
        UserByDto userByDto = new UserByDto();
        userByDto.setId(createdBy.getId());
        userByDto.setUsername(createdBy.getUsername());
        userByDto.setFullName(createdBy.getFullName());
        userByDto.setEmailAddress(createdBy.getEmailAddress());
        return userByDto;
    }
}
