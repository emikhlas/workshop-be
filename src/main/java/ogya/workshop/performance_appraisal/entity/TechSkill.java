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
@Table(name = "TECHNICAL_SKILL")
public class TechSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;
    @Column(name = "TECHNICAL_SKILL", length = 100 ,nullable = false)
    private String techSkill;
    @Column(name = "ENABLED")
    private Boolean enabled = true;
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDate createdAt;
    @Column(name = "CREATED_BY")
    private UUID createdBy;
    @Column(name = "UPDATED_AT")
    private LocalDate updatedAt;
    @Column(name = "UPDATED_BY")
    private UUID updatedBy;
}
