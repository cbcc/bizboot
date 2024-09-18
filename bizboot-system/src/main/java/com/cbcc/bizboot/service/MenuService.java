package com.cbcc.bizboot.service;

import com.cbcc.bizboot.entity.Menu;
import com.cbcc.bizboot.entity.dto.MenuQueryDTO;

import java.util.List;

public interface MenuService {

    List<Menu> find(MenuQueryDTO menuQueryDTO);

    Menu get(long id);

    Menu create(Menu menu);

    void update(Menu menu);

    void delete(long id);
}
