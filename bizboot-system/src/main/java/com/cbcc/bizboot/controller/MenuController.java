package com.cbcc.bizboot.controller;

import com.cbcc.bizboot.entity.Menu;
import com.cbcc.bizboot.entity.model.MenuModel;
import com.cbcc.bizboot.enums.MenuType;
import com.cbcc.bizboot.exception.BadRequestException;
import com.cbcc.bizboot.service.MenuService;
import com.cbcc.bizboot.util.BeanUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "菜单接口")
@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Operation(summary = "列表查询")
    @GetMapping
    List<Menu> find(Menu menu) {
        return menuService.find(menu);
    }

    @Operation(summary = "查询")
    @GetMapping("/{id}")
    Menu get(@PathVariable Long id) {
        return menuService.get(id);
    }

    @Operation(summary = "创建")
    @PostMapping
    Menu create(@Valid @RequestBody MenuModel menuModel) {
        Menu menu = BeanUtils.newAndCopy(menuModel, Menu.class);
        return menuService.create(menu);
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    void update(@PathVariable Long id, @Valid @RequestBody MenuModel menuModel) {
        if (menuModel.getType().equals(MenuType.BUTTON.key())) {
            if (StringUtils.isBlank(menuModel.getAuths())) {
                throw new BadRequestException("[auths]不能为空");
            }
        } else {
            if (StringUtils.isBlank(menuModel.getName())) {
                throw new BadRequestException("[name]不能为空");
            }
            if (StringUtils.isBlank(menuModel.getPath())) {
                throw new BadRequestException("[path]不能为空");
            }
        }
        Menu menu = BeanUtils.newAndCopy(menuModel, Menu.class);
        menu.setId(id);
        menuService.update(menu);
    }

    @Operation(summary = "删除", description = "删除菜单及其所有子菜单")
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        menuService.delete(id);
    }
}
