package ogya.workshop.performance_appraisal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "ACHIEVEMENT")
public class Achieve {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Column(name = "ACHIEVEMENT_NAME", length = 100, nullable = false)
    private String achievementName;

    @ManyToOne
    @JoinColumn(name = "GROUP_ACHIEVEMENT_ID")
    private GroupAchieve groupAchieve;

    @Column(name = "ENABLED", nullable = false)
    private int enabled = 1;

    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private User createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "UPDATED_BY")
    private User updatedBy;

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
