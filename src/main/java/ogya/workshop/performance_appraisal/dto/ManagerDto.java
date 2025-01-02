package ogya.workshop.performance_appraisal.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ogya.workshop.performance_appraisal.util.PageInfo;
import ogya.workshop.performance_appraisal.util.ResponseDetail;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class ManagerDto<T> {
    @JsonProperty("info")
    private ResponseDetail info;

    @JsonProperty("page_info")
    private PageInfo pageInfo;

    @JsonProperty("total_rows")
    private int totalRows;

    @JsonProperty("content")
    private T content;
}
