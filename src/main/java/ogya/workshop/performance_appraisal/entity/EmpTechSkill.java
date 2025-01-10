package ogya.workshop.performance_appraisal.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "TECHNICAL_SKILL_ID", nullable = false)
    private TechSkill techSkill;

    @Column(name = "TECH_DETAIL")
    private String techDetail;

    @Column(name = "SCORE", length = 3, nullable = false)
    private int score;

    @Column(name = "ASSESSMENT_YEAR", length = 4, nullable = false)
    private int assessmentYear;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "CREATED_BY")
    private User createdBy;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "UPDATED_BY")
    private User updatedBy;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
}
