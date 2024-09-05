package com.cbcc.bizboot.service;

import com.cbcc.bizboot.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {

    Page<Role> find(Role role, Pageable pageable);

    Role get(long id);

    Role create(Role role);

    void update(Role role);

    void updateEnabled(long id, boolean enabled);

    void delete(long id);
}
