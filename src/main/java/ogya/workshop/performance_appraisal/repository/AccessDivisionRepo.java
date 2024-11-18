package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.entity.AccessDivision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccessDivisionRepo extends JpaRepository<AccessDivision, UUID> {
}
