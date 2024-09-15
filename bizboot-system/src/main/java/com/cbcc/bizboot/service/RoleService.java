package com.cbcc.bizboot.service;

import com.cbcc.bizboot.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {

    Page<Role> find(Role role, Pageable pageable);

    Role get(long id);

    Role create(Role role);

    void update(Role role);

    void updateEnabled(long id, boolean enabled);

    List<Long> getMenuIds(long id);

    void updateMenus(long id, List<Long> menuIds);

    void delete(long id);
}
