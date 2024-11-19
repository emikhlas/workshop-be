package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, UUID> {

    List<UserRole> findByUserId(UUID id);
}
