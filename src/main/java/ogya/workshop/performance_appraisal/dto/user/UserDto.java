package ogya.workshop.performance_appraisal.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.dto.division.DivisionInfoDto;
import ogya.workshop.performance_appraisal.dto.role.RoleDto;
import ogya.workshop.performance_appraisal.dto.role.RoleInfoDto;
import ogya.workshop.performance_appraisal.entity.Role;
import ogya.workshop.performance_appraisal.entity.User;

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
    private Set<RoleInfoDto> role;
    @JsonProperty("division")
    private DivisionInfoDto division;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("created_by")
    private UserInfoDto createdBy;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    @JsonProperty("updated_by")
    private UserInfoDto updatedBy;


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
        userDto.setDivision(DivisionInfoDto.fromEntity(user.getDivision()));
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());

        if (user.getCreatedBy() != null) {
            userDto.setCreatedBy(UserInfoDto.fromEntity(user.getCreatedBy()));
        }
        if (user.getUpdatedBy() != null) {
            userDto.setUpdatedBy(UserInfoDto.fromEntity(user.getUpdatedBy()));
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

    public static UserDto fromAuthenticatedUser(AuthUser authUser) {
        UserDto userDto = new UserDto();
        userDto.setId(authUser.getUser().getId());
        userDto.setUsername(authUser.getUser().getUsername());
        userDto.setFullName(authUser.getUser().getFullName());
        userDto.setEmailAddress(authUser.getUser().getEmailAddress());
        return userDto;
    }
}
