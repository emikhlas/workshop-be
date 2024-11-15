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
@Table(name = "APP_ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;
    @Column(name = "ROLENAME", nullable = false)
    private String rolename;
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDate createdAt;
    @Column(name = "CREATED_BY")
    private UUID createdBy;
    @Column(name = "UPDATED_AT")
    private LocalDate updatedAt;
    @Column(name = "UPDATED_BY")
    private UUID updatedBy;
}
