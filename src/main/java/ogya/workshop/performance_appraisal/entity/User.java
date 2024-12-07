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
@Table(name = "APP_USER")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", nullable = false)
    private UUID id;

    @Column(name = "USERNAME", length = 30, nullable = false)
    private String username;

    @Column(name = "FULL_NAME", length = 60, nullable = false)
    private String fullName;

    @Column(name = "POSITION", length = 50, nullable = false)
    private String position;

    @Column(name = "EMAIL_ADDRESS", length = 60, nullable = false)
    private String emailAddress;

    @Column(name = "EMPLOYEE_STATUS", nullable = false)
    private int employeeStatus; // 1: kontrak, 2: permanen

    @Column(name = "JOIN_DATE", nullable = false)
    private LocalDate joinDate;

    @Column(name = "ENABLED", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private int enabled = 1; // 0: disabled, 1: enabled

    @Column(name = "PASSWORD", length = 100, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "DIVISION_ID", nullable = false)
    private Division division;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATED_BY")
    private User createdBy;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPDATED_BY")
    private User updatedBy;
}
