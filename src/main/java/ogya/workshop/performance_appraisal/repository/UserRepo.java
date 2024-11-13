package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

}
