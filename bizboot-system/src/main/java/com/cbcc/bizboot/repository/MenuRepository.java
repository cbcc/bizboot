package com.cbcc.bizboot.repository;

import com.cbcc.bizboot.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("select m.id from Menu m where m.parentId = :parentId")
    List<Long> findIdsByParentId(long parentId);
}
