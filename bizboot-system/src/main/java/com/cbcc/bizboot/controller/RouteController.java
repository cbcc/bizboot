package com.cbcc.bizboot.controller;

import com.cbcc.bizboot.entity.vo.Route;
import com.cbcc.bizboot.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "路由接口")
@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @Operation(summary = "查询当前用户路由")
    @GetMapping("/user")
    List<Route> getByCurrentUser() {
        return routeService.getByCurrentUser();
    }
}
