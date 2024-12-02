package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.dto.menu.MenuInfoDto;
import ogya.workshop.performance_appraisal.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface MenuRepo extends JpaRepository<Menu, UUID> {

    @Query(value = "SELECT m.id, m.menu_name, m.created_at, m.updated_at, m.created_by, m.updated_by FROM app_user_role ur " +
            "JOIN app_role_menu rm ON ur.role_id = rm.role_id " +
            "JOIN app_menu m ON rm.menu_id = m.id " +
            "WHERE ur.user_id = :userId", nativeQuery = true)
    List<Menu> findMenuByUserId(@Param("userId") UUID userId);
}
