package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.config.security.Auth.AuthUser;
import ogya.workshop.performance_appraisal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

    User findByUsername(String username);
}
