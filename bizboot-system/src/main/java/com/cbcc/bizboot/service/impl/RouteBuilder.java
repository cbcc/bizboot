package com.cbcc.bizboot.service.impl;

import com.cbcc.bizboot.entity.Menu;
import com.cbcc.bizboot.entity.vo.Route;
import com.cbcc.bizboot.enums.MenuType;
import com.cbcc.bizboot.util.BeanUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RouteBuilder {

    private final List<Menu> menus;

    public RouteBuilder(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Route> build() {
        // 排序
        List<Menu> menus = this.menus.stream().sorted(Comparator.comparing(Menu::getSort)).toList();

        // 其他菜单
        List<Menu> otherMenus = menus.stream()
                .filter(it -> !Objects.equals(it.getType(), MenuType.BUTTON.key())).toList();
        Map<Long, Route> routeMap = otherMenus.stream().collect(Collectors.toMap(Menu::getId, this::transformToRoute));
        List<Route> rootRoutes = new ArrayList<>();
        for (Menu menu : otherMenus) {
            Long parentId = menu.getParentId();
            if (parentId == 0) {
                rootRoutes.add(routeMap.get(menu.getId()));
            } else {
                Route parentRoute = routeMap.get(parentId);
                if (parentRoute != null) {
                    parentRoute.addChild(routeMap.get(menu.getId()));
                }
            }
        }
        // 按钮
        List<Menu> buttonMenus = menus.stream().filter(it -> Objects.equals(it.getType(), MenuType.BUTTON.key())).toList();
        for (Menu button : buttonMenus) {
            Long parentId = button.getParentId();
            if (parentId != 0) {
                Route parentRoute = routeMap.get(parentId);
                if (parentRoute != null) {
                    parentRoute.getMeta().addAuth(button.getAuths());
                }
            }
        }
        return rootRoutes;
    }

    private Route transformToRoute(Menu menu) {
        Route route = new Route();
        route.setName(menu.getName());
        route.setPath(menu.getPath());
        route.setComponent(menu.getComponent());
        Route.Meta meta = BeanUtils.newAndCopy(menu, Route.Meta.class);
        route.setMeta(meta);
        return route;
    }
}
