package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaSpecificationExecutor<User>, JpaRepository<User, UUID> {
    User findByUsername(String username);
    @Query("SELECT u.id FROM User u")
    List<UUID> findAllId();
    Boolean existsByUsername(String username);
    Boolean existsByEmailAddress(String email);
}
