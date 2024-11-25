package ogya.workshop.performance_appraisal.dto.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.dto.user.UserInfoDto;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class MenuDto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("menu_name")
    private String menuName;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("created_by")
    private UserInfoDto createdBy;
    @JsonProperty("updated_at")
    private Date updatedAt;
    @JsonProperty("updated_by")
    private UserInfoDto updatedBy;
}
