package com.cbcc.bizboot.service;

import com.cbcc.bizboot.entity.Dept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeptService {

    Page<Dept> find(Dept dept, Pageable pageable);

    Dept get(long id);

    Dept create(Dept dept);

    void update(Dept dept);

    void delete(long id);
}
