package ogya.workshop.performance_appraisal.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
@Entity
@Table(name = "APP_USER_ROLE")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Role role;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
}
