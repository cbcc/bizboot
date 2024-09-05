package com.cbcc.bizboot.repository;

import com.cbcc.bizboot.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<Dept, Long> {
}
