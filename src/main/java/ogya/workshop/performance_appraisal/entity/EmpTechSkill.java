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
    @Column(name = "CREATED_BY")
    private UUID createdBy;
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDate createdAt;
    @Column(name = "UPDATED_BY")
    private UUID updatedBy;
    @Column(name = "UPDATED_AT")
    private LocalDate updatedAt;
}
