package com.cbcc.bizboot.controller;

import com.cbcc.bizboot.entity.Dept;
import com.cbcc.bizboot.entity.User;
import com.cbcc.bizboot.entity.model.EnabledModel;
import com.cbcc.bizboot.entity.model.UserModel;
import com.cbcc.bizboot.service.UserService;
import com.cbcc.bizboot.util.BeanUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

@Tag(name = "用户接口")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "分页查询")
    @GetMapping
    PagedModel<User> find(User user, Pageable pageable) {
        return new PagedModel<>(userService.find(user, pageable));
    }

    @Operation(summary = "查询")
    @GetMapping("/{id}")
    User get(@PathVariable Long id) {
        return userService.get(id);
    }

    @Operation(summary = "创建")
    @PostMapping
    User create(@Valid @RequestBody UserModel userModel) {
        User user = BeanUtils.newAndCopy(userModel, User.class);
        user.setDept(new Dept(userModel.getDeptId()));
        return userService.create(user);
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    void update(@PathVariable Long id, @Valid @RequestBody UserModel userModel) {
        User user = BeanUtils.newAndCopy(userModel, User.class);
        user.setId(id);
        user.setDept(new Dept(userModel.getDeptId()));
        userService.update(user);
    }

    @Operation(summary = "修改是否启用状态")
    @PatchMapping("/{id}/enabled")
    void updateEnabled(@PathVariable Long id, @RequestBody EnabledModel enabledModel) {
        userService.updateEnabled(id, enabledModel.getEnabled());
    }

    @Operation(summary = "查询用户角色 id 列表")
    @GetMapping("/{id}/roles")
    List<Long> getRoleIds(@PathVariable Long id) {
        return userService.getRoleIds(id);
    }

    @Operation(summary = "修改用户角色")
    @PatchMapping("/{id}/roles")
    void updateRoles(@PathVariable Long id, @RequestBody List<Long> roleIds) {
        userService.updateRoles(id, roleIds);
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}