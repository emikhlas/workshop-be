package ogya.workshop.performance_appraisal.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "ACHIEVEMENT")
public class Achieve {

    @Id
    @Column(name = "ID", length = 32, nullable = false)
    private String id;

    @Column(name = "ACHIEVEMENT_NAME", length = 100, nullable = false)
    private String achievementName;

    @Column(name = "GROUP_ACHIEVEMENT_ID", length = 32)
    private String groupAchievementId;

    @Column(name = "ENABLED", nullable = false)
    private Integer enabled = 1;

    @Column(name = "CREATED_BY", length = 32)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "UPDATED_BY", length = 32)
    private String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT", nullable = false)
    private Date updatedAt;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
    }
}
