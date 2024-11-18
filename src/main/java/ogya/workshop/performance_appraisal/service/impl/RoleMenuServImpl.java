package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.MenuDto;
import ogya.workshop.performance_appraisal.dto.RoleMenuDto;
import ogya.workshop.performance_appraisal.entity.Menu;
import ogya.workshop.performance_appraisal.entity.RoleMenu;
import ogya.workshop.performance_appraisal.repository.RoleMenuRepo;
import ogya.workshop.performance_appraisal.service.RoleMenuServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleMenuServImpl implements RoleMenuServ {

    @Autowired
    private RoleMenuRepo roleMenuRepo;

    // Create a new Group Achieve
    @Override
    public RoleMenuDto createRoleMenu(RoleMenuDto roleMenuDto) {
        RoleMenu roleMenu = convertToEntity(roleMenuDto);
        RoleMenu savedRoleMenu = roleMenuRepo.save(roleMenu);
        return convertToDto(savedRoleMenu);
    }

    // Update an existing Achieve
    @Override
    public RoleMenuDto updateRoleMenu(UUID id, RoleMenuDto roleMenuDto) {
        if (!roleMenuRepo.existsById(id)) {
            throw new IllegalArgumentException("Group Achievement with this ID does not exist.");
        }

        RoleMenu roleMenu = convertToEntity(roleMenuDto);
        roleMenu.setId(id);  // Use the ID from the URL path

        RoleMenu updatedRoleMenu = roleMenuRepo.save(roleMenu);
        return convertToDto(updatedRoleMenu);
    }

    // Retrieve by ID
    @Override
    public Optional<RoleMenuDto> getRoleMenuById(UUID id) {
        Optional<RoleMenu> roleMenu = roleMenuRepo.findById(id);
        return roleMenu.map(this::convertToDto);
    }

    // Retrieve all Achievements
    @Override
    public List<RoleMenuDto> getAllRoleMenu() {
        List<RoleMenu> roleMenus = roleMenuRepo.findAll();
        return roleMenus.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Delete an Achieve by ID
    @Override
    public boolean deleteRoleMenu(UUID id) {
        roleMenuRepo.deleteById(id);
        return true;
    }

    // Helper method to convert Achieve entity to AchieveDto
    private RoleMenuDto convertToDto(RoleMenu roleMenu) {
        RoleMenuDto roleMenuDto = new RoleMenuDto();
        roleMenuDto.setId(roleMenu.getId());
        roleMenuDto.setRoleId(roleMenu.getRoleId());
        roleMenuDto.setMenuId(roleMenu.getMenuId());
        return roleMenuDto;
    }

    // Helper method to convert AchieveDto to Achieve entity
    private RoleMenu convertToEntity(RoleMenuDto roleMenuDto) {
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setId(roleMenuDto.getId());
        roleMenu.setRoleId(roleMenuDto.getRoleId());
        roleMenu.setMenuId(roleMenuDto.getMenuId());
        return roleMenu;
    }
}
