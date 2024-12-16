package ogya.workshop.performance_appraisal.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "DEV_PLAN_ID")
    private DevPlan devPlan;

    @Column(name = "PLAN_DETAIL")
    private String planDetail;

    @Column(name = "ASSESSMENT_YEAR", length = 4)
    private int assessmentYear;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "CREATED_BY")
    private User createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "UPDATED_BY")
    private User updatedBy;

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
