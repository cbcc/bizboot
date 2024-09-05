package com.cbcc.bizboot.repository;

import com.cbcc.bizboot.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findByUserId(long userId);
}
