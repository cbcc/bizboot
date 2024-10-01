package com.cbcc.bizboot.service;

import com.cbcc.bizboot.AbstractIntegrationTest;
import com.cbcc.bizboot.SqlCleanup;
import com.cbcc.bizboot.entity.Dept;
import com.cbcc.bizboot.entity.dto.DeptQueryDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

@SqlCleanup
public class DeptServiceTest extends AbstractIntegrationTest {

    private final DeptService deptService;

    @Autowired
    public DeptServiceTest(DeptService deptService) {
        this.deptService = deptService;
    }

    @Test
    @Sql(scripts = "/sql/dept/dept-find-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testFind() {
        DeptQueryDTO deptQueryDTO = new DeptQueryDTO();
        Page<Dept> depts = deptService.find(deptQueryDTO, PageRequest.of(0, 20));
        Assertions.assertEquals(depts.getTotalElements(), 2L);
    }

    @Test
    public void testCreate() {
        Dept dept = new Dept();
        dept.setParentId(0L);
        dept.setName("dept");
        dept.setType(0);
        dept.setEnabled(true);
        Dept result = deptService.create(dept);
        Assertions.assertNotNull(result.getId());
    }
}
