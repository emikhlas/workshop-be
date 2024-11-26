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
@Table(name = "TECHNICAL_SKILL")
public class TechSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;
    @Column(name = "TECHNICAL_SKILL", length = 100 ,nullable = false)
    private String techSkill;
    @Column(name = "ENABLED")
    private int enabled = 1;
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private User createdBy;
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "UPDATED_BY")
    private User updatedBy;
}
