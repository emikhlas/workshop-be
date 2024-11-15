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
@Table(name = "GROUP_ATTITUDE_SKILL")
public class GroupAttitudeSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Column(name = "GROUP_NAME", length = 100, nullable = false)
    private String groupName;

    @Column(name = "PERCENTAGE")
    private int percentage;

    @Column(name = "ENABLED", nullable = false)
    private Integer enabled = 1;

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
