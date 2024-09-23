package com.cbcc.bizboot.repository;

import com.cbcc.bizboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

    Optional<Role> findByCode(String code);

    @Modifying
    @Query("UPDATE Role r SET r.enabled = :enabled WHERE r.id = :id")
    void updateEnabledById(long id, boolean enabled);
}
