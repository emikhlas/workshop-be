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
@Table(name = "EMP_TECHNICAL_SKILL")
public class EmpTechSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "TECHNICAL_SKILL_ID", nullable = false)
    private TechSkill techSkill;
    @Column(name = "SCORE", length = 3, nullable = false)
    private int score;
    @Column(name = "ASSESSMENT_YEAR", length = 4, nullable = false)
    private int assessmentYear;
    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private User createdBy;
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "UPDATED_BY")
    private User updatedBy;
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}
