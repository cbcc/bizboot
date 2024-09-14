package com.cbcc.bizboot.service;

import com.cbcc.bizboot.entity.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> find(Menu menu);

    Menu get(long id);

    Menu create(Menu menu);

    void update(Menu menu);

    void delete(long id);
}
