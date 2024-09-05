package com.cbcc.bizboot.service;

import com.cbcc.bizboot.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuService {

    Page<Menu> find(Menu menu, Pageable pageable);

    Menu get(long id);

    Menu create(Menu menu);

    void update(Menu menu);

    void delete(long id);
}
