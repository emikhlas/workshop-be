package ogya.workshop.performance_appraisal.dto.assesssum;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GroupedResultDto<T> {
    @JsonProperty("section")
    private String section;
    @JsonProperty("group_name")
    private String groupName;
    @JsonProperty("total_score")
    private Integer totalScore;
    @JsonProperty("percentage")
    private double percentage;
    @JsonProperty("items")
    private List<T> items;

    @Override
    public String toString() {
        return "GroupedResultDto{" +
                "section='" + section + '\'' +
                ", groupName='" + groupName + '\'' +
                ", totalScore=" + totalScore +
                ", percentage=" + percentage +
                ", items=" + items + // Add this line to include 'items' in the output
                '}';
    }
}
