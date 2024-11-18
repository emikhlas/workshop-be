package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleMenuRepo extends JpaRepository<RoleMenu, UUID> {
}
