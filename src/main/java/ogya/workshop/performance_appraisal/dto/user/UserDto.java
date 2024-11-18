package ogya.workshop.performance_appraisal.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.DivisionDto;
import ogya.workshop.performance_appraisal.entity.User;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserDto {
    @JsonProperty("id")
    private UUID id;
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
    private LocalDate joinDate;
    @JsonProperty("enabled")
    private Integer enabled;
    @JsonProperty("password")
    private String password;
    @JsonProperty("role")
    private Set<String> role;
    @JsonProperty("division")
    private DivisionDto division;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("created_by")
    private UserDto createdBy;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("updated_by")
    private UserDto updatedBy;


    public static UserDto fromEntity(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFullName(user.getFullName());
        userDto.setPosition(user.getPosition());
        userDto.setEmailAddress(user.getEmailAddress());
        userDto.setEmployeeStatus(user.getEmployeeStatus());
        userDto.setJoinDate(user.getJoinDate());
        userDto.setEnabled(user.getEnabled());
        userDto.setPassword(user.getPassword());
        userDto.setDivision(DivisionDto.fromEntity(user.getDivision()));
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());

        if (user.getCreatedBy() != null) {
            UserDto createdBy = new UserDto();
            createdBy.setId(user.getCreatedBy().getId());
            createdBy.setUsername(user.getCreatedBy().getUsername());
            createdBy.setFullName(user.getCreatedBy().getFullName());
            userDto.setCreatedBy(createdBy);
        }
        if (user.getUpdatedBy() != null) {
            UserDto updatedBy = new UserDto();
            updatedBy.setId(user.getUpdatedBy().getId());
            updatedBy.setUsername(user.getUpdatedBy().getUsername());
            updatedBy.setFullName(user.getUpdatedBy().getFullName());
            userDto.setUpdatedBy(updatedBy);
        }
        return userDto;
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setFullName(userDto.getFullName());
        user.setPosition(userDto.getPosition());
        user.setEmailAddress(userDto.getEmailAddress());
        user.setEmployeeStatus(userDto.getEmployeeStatus());
        user.setJoinDate(userDto.getJoinDate() != null ? userDto.getJoinDate() : null);
        user.setEnabled(userDto.getEnabled());
        user.setPassword(userDto.getPassword());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());
        return user;
    }
}
