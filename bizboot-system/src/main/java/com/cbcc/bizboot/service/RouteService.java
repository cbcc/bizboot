package com.cbcc.bizboot.service;

import com.cbcc.bizboot.entity.vo.Route;

import java.util.List;

public interface RouteService {

    List<Route> getByCurrentUser();
}
