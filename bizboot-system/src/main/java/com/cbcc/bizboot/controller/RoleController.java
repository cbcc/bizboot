package com.cbcc.bizboot.controller;

import com.cbcc.bizboot.entity.Role;
import com.cbcc.bizboot.entity.model.EnabledModel;
import com.cbcc.bizboot.entity.model.RoleModel;
import com.cbcc.bizboot.service.RoleService;
import com.cbcc.bizboot.util.BeanUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "角色接口")
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "分页查询")
    @GetMapping
    PagedModel<Role> find(Role role, Pageable pageable) {
        return new PagedModel<>(roleService.find(role, pageable));
    }

    @Operation(summary = "查询")
    @GetMapping("/{id}")
    Role get(@PathVariable Long id) {
        return roleService.get(id);
    }

    @Operation(summary = "创建")
    @PostMapping
    Role create(@Valid @RequestBody RoleModel roleModel) {
        Role role = BeanUtils.newAndCopy(roleModel, Role.class);
        return roleService.create(role);
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    void update(@PathVariable Long id, @Valid @RequestBody RoleModel roleModel) {
        Role role = BeanUtils.newAndCopy(roleModel, Role.class);
        role.setId(id);
        roleService.update(role);
    }

    @Operation(summary = "修改是否启用状态")
    @PatchMapping("/{id}/enabled")
    void updateEnabled(@PathVariable Long id, @RequestBody EnabledModel enabledModel) {
        roleService.updateEnabled(id, enabledModel.getEnabled());
    }

    @Operation(summary = "查询角色菜单 id 列表")
    @GetMapping("/{id}/menus")
    List<Long> getRoleIds(@PathVariable Long id) {
        return roleService.getMenuIds(id);
    }

    @Operation(summary = "修改角色菜单")
    @PatchMapping("/{id}/menus")
    void updateRoles(@PathVariable Long id, @RequestBody List<Long> menuIds) {
        roleService.updateMenus(id, menuIds);
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        roleService.delete(id);
    }
}
