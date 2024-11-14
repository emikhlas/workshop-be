package ogya.workshop.performance_appraisal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "ASSESSMENT_SUMMARY")
public class AssessSum {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;
    @Column(name = "YEAR", length = 4, nullable = false)
    private int year;
    @Column(name = "SCORE", length = 3, nullable = false)
    private int score;
    @Column(name = "STATUS", length = 1, nullable = false)
    private int status;
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDate createdAt;
    @Column(name = "CREATED_BY")
    private UUID createdBy;
    @Column(name = "UPDATED_AT")
    private LocalDate updatedAt;
    @Column(name = "UPDATED_BY")
    private UUID updatedBy;
}
