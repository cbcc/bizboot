package com.cbcc.bizboot.repository;

import com.cbcc.bizboot.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleMenuRepository extends JpaRepository<RoleMenu, Long> {

    List<RoleMenu> findByRoleId(long roleId);

    List<RoleMenu> findByRoleIdIn(Iterable<Long> roleIds);
}
