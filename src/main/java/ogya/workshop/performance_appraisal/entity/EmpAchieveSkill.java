package ogya.workshop.performance_appraisal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "EMP_ACHIEVEMENT_SKILL")
public class EmpAchieveSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ACHIEVEMENT_ID")
    private Achieve achieve;

    @Column(name = "SCORE", length = 3)
    private int score;

    @Column(name = "ASSESSMENT_YEAR", length = 4)
    private int assessmentYear;

    @Column(name = "CREATED_BY")
    private UUID createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "UPDATED_BY")
    private UUID updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    // Set createdAt before the entity is persisted
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
    }

    // Set updatedAt before the entity is updated
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }
}
