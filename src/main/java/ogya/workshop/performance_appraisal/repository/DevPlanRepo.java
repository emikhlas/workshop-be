package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.entity.DevPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DevPlanRepo extends JpaRepository<DevPlan, UUID> {

}
