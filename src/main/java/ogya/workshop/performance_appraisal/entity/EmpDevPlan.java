package ogya.workshop.performance_appraisal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "EMP_DEV_PLAN")
public class EmpDevPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Column(name = "USER_ID")
    private UUID userId;

    @Column(name = "DEV_PLAN_ID")
    private UUID devPlanId;

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

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }
}
