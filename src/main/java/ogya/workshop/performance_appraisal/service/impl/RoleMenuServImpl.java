package ogya.workshop.performance_appraisal.service.impl;

import ogya.workshop.performance_appraisal.dto.menu.MenuInfoDto;
import ogya.workshop.performance_appraisal.dto.role.RoleInfoDto;
import ogya.workshop.performance_appraisal.dto.rolemenu.RoleMenuByRoleDto;
import ogya.workshop.performance_appraisal.dto.rolemenu.RoleMenuCreateDto;
import ogya.workshop.performance_appraisal.dto.rolemenu.RoleMenuDto;
import ogya.workshop.performance_appraisal.dto.rolemenu.RoleMenuUpdateDto;
import ogya.workshop.performance_appraisal.entity.Menu;
import ogya.workshop.performance_appraisal.entity.Role;
import ogya.workshop.performance_appraisal.entity.RoleMenu;
import ogya.workshop.performance_appraisal.repository.RoleMenuRepo;
import ogya.workshop.performance_appraisal.repository.RoleRepo;
import ogya.workshop.performance_appraisal.service.RoleMenuServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleMenuServImpl implements RoleMenuServ {

    @Autowired
    private RoleMenuRepo roleMenuRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public RoleMenuDto createRoleMenu(RoleMenuCreateDto roleMenuDto) {
        RoleMenu roleMenu = convertToEntity(roleMenuDto);
        RoleMenu savedRoleMenu = roleMenuRepo.save(roleMenu);
        return convertToDto(savedRoleMenu);
    }

    @Override
    public RoleMenuDto updateRoleMenu(UUID id, RoleMenuCreateDto roleMenuDto) {
        if (!roleMenuRepo.existsById(id)) {
            throw new IllegalArgumentException("Group Achievement with this ID does not exist.");
        }

        RoleMenu roleMenu = convertToEntity(roleMenuDto);
        roleMenu.setId(id);

        RoleMenu updatedRoleMenu = roleMenuRepo.save(roleMenu);
        return convertToDto(updatedRoleMenu);
    }

    @Override
    public Optional<RoleMenuDto> getRoleMenuById(UUID id) {
        Optional<RoleMenu> roleMenu = roleMenuRepo.findById(id);
        return roleMenu.map(this::convertToDto);
    }

    @Override
    public List<RoleMenuDto> getAllRoleMenu() {
        List<RoleMenu> roleMenus = roleMenuRepo.findAll();
        return roleMenus.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public boolean deleteRoleMenu(UUID id) {
        roleMenuRepo.deleteById(id);
        return true;
    }

    @Override
    public List<RoleMenuDto> updateRoleMenuByRoleId(UUID roleId, RoleMenuUpdateDto roleMenuUpdateDto) {
        List<RoleMenu> roleMenuList = roleMenuRepo.findByRoleId(roleId);
        roleMenuRepo.deleteAll(roleMenuList);
        for (UUID menuId : roleMenuUpdateDto.getMenu()) {
            RoleMenu roleMenu = new RoleMenu();
            Role role = new Role();
            role.setId(roleId);
            Menu menu = new Menu();
            menu.setId(menuId);
            roleMenu.setRole(role);
            roleMenu.setMenu(menu);
            roleMenuRepo.save(roleMenu);
        }
        return roleMenuRepo.findByRoleId(roleId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<RoleMenuByRoleDto> getAllRoleMenuByRole() {
        List<Role> roleList = roleRepo.findAll();
        List<RoleMenuByRoleDto> roleMenuByRoleDtoList = new ArrayList<>();
        for(Role role : roleList) {
            List<RoleMenu> roleMenuList = roleMenuRepo.findByRoleId(role.getId());

            RoleMenuByRoleDto roleMenuByRoleDto = new RoleMenuByRoleDto();
            RoleInfoDto roleInfoDto = new RoleInfoDto();
            roleInfoDto.setId(role.getId());
            roleInfoDto.setRolename(role.getRolename());
            roleMenuByRoleDto.setRole(roleInfoDto);

            List<MenuInfoDto> menuInfoDtoList = roleMenuList.stream().map(roleMenu -> {
                MenuInfoDto menuInfoDto = new MenuInfoDto();
                menuInfoDto.setId(roleMenu.getMenu().getId());
                return menuInfoDto;
            }).collect(Collectors.toList());
            roleMenuByRoleDto.setMenu(menuInfoDtoList);
            roleMenuByRoleDtoList.add(roleMenuByRoleDto);
        }
        return roleMenuByRoleDtoList;
    }

    private RoleMenuDto convertToDto(RoleMenu roleMenu) {
        RoleMenuDto roleMenuDto = new RoleMenuDto();
        roleMenuDto.setId(roleMenu.getId());
        if (roleMenu.getRole() != null) {
            roleMenuDto.setRoleId(roleMenu.getRole().getId());
        }
        if (roleMenu.getMenu() != null) {
            roleMenuDto.setMenuId(roleMenu.getMenu().getId());
        }
        return roleMenuDto;
    }

    private RoleMenu convertToEntity(RoleMenuCreateDto roleMenuDto) {
        RoleMenu roleMenu = new RoleMenu();
        if (roleMenuDto.getRoleId() != null) {
            Role role = new Role();
            role.setId(roleMenuDto.getRoleId());
            roleMenu.setRole(role);
        }
        if (roleMenuDto.getMenuId() != null) {
            Menu menu = new Menu();
            menu.setId(roleMenuDto.getMenuId());
            roleMenu.setMenu(menu);
        }
        return roleMenu;
    }
}
