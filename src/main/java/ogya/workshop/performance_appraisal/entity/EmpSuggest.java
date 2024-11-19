package ogya.workshop.performance_appraisal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "EMP_SUGGESTION")
public class EmpSuggest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
    @Column(name = "SUGGESTION", length = 200,nullable = false)
    private String suggestion;
    @Column(name = "ASSESSMENT_YEAR", length = 4)
    private Integer assessmentYear;
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private User createdBy;
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "UPDATED_BY")
    private User updatedBy;
}
