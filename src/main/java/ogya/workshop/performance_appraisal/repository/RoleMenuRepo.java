package ogya.workshop.performance_appraisal.repository;

import ogya.workshop.performance_appraisal.dto.menu.MenuInfoDto;
import ogya.workshop.performance_appraisal.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RoleMenuRepo extends JpaRepository<RoleMenu, UUID> {
    List<RoleMenu> findByRoleId(UUID roleId);
}
