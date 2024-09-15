package com.cbcc.bizboot.service.impl;

import com.cbcc.bizboot.component.UserInfoHolder;
import com.cbcc.bizboot.entity.Menu;
import com.cbcc.bizboot.entity.RoleMenu;
import com.cbcc.bizboot.entity.User;
import com.cbcc.bizboot.entity.UserRole;
import com.cbcc.bizboot.entity.vo.Route;
import com.cbcc.bizboot.repository.MenuRepository;
import com.cbcc.bizboot.repository.RoleMenuRepository;
import com.cbcc.bizboot.repository.UserRoleRepository;
import com.cbcc.bizboot.service.RoleService;
import com.cbcc.bizboot.service.RouteService;
import com.cbcc.bizboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private final UserInfoHolder userInfoHolder;

    private final UserService userService;

    private final MenuRepository menuRepository;

    private final UserRoleRepository userRoleRepository;

    private final RoleMenuRepository roleMenuRepository;

    public RouteServiceImpl(UserInfoHolder userInfoHolder, UserService userService,
                            RoleService roleService, MenuRepository menuRepository,
                            UserRoleRepository userRoleRepository, RoleMenuRepository roleMenuRepository) {
        this.userInfoHolder = userInfoHolder;
        this.userService = userService;
        this.menuRepository = menuRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleMenuRepository = roleMenuRepository;
    }

    @Override
    public List<Route> getByCurrentUser() {
        String username = userInfoHolder.getUserInfo().getUsername();
        User user = userService.findByUsername(username);

        // 查询用户角色
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        if (userRoles.isEmpty()) {
            return Collections.emptyList();
        }
        // 查询角色菜单
        List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).toList();
        List<RoleMenu> roleMenus = roleMenuRepository.findByRoleIdIn(roleIds);
        if (roleMenus.isEmpty()) {
            return Collections.emptyList();
        }
        // 查询菜单
        List<Long> menuIds = roleMenus.stream().map(RoleMenu::getMenuId).toList();
        List<Menu> menus = menuRepository.findAllById(menuIds);

        return new RouteBuilder(menus).build();
    }
}
