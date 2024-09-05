package com.cbcc.bizboot.controller;

import com.cbcc.bizboot.entity.Menu;
import com.cbcc.bizboot.entity.model.MenuModel;
import com.cbcc.bizboot.service.MenuService;
import com.cbcc.bizboot.util.BeanUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "菜单接口")
@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Operation(summary = "分页查询")
    @GetMapping
    PagedModel<Menu> find(Menu menu, Pageable pageable) {
        return new PagedModel<>(menuService.find(menu, pageable));
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
        Menu menu = BeanUtils.newAndCopy(menuModel, Menu.class);
        menu.setId(id);
        menuService.update(menu);
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        menuService.delete(id);
    }
}
