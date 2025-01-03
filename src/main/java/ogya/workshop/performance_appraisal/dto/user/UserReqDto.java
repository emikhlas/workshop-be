package ogya.workshop.performance_appraisal.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.entity.User;
import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserReqDto {

    @JsonProperty("username")
    private String username;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("position")
    private String position;
    @JsonProperty("email_address")
    private String emailAddress;
    @JsonProperty("employee_status")
    private Integer employeeStatus;
    @JsonProperty("join_date")
    private Date joinDate;
    @JsonProperty("enabled")
    private Integer enabled;
    @JsonProperty("password")
    private String password;
    @JsonProperty("role")
    private Set<UUID> role;
    @JsonProperty("division_id")
    private UUID divisionId;

    public static User toEntity(UserReqDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername().toLowerCase());
        user.setFullName(userDto.getFullName());
        user.setPosition(userDto.getPosition());
        user.setEmailAddress(userDto.getEmailAddress().toLowerCase());
        user.setEmployeeStatus(userDto.getEmployeeStatus());
        user.setJoinDate(userDto.getJoinDate() != null ? userDto.getJoinDate().toLocalDate() : null);
        user.setEnabled(userDto.getEnabled());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
