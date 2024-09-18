package com.cbcc.bizboot.controller;

import com.cbcc.bizboot.entity.Dept;
import com.cbcc.bizboot.entity.dto.DeptQueryDTO;
import com.cbcc.bizboot.entity.dto.model.DeptModel;
import com.cbcc.bizboot.service.DeptService;
import com.cbcc.bizboot.util.BeanUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "部门接口")
@RestController
@RequestMapping("/api/depts")
public class DeptController {

    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @Operation(summary = "分页查询")
    @GetMapping
    PagedModel<Dept> find(DeptQueryDTO deptQueryDTO, Pageable pageable) {
        return new PagedModel<>(deptService.find(deptQueryDTO, pageable));
    }

    @Operation(summary = "查询")
    @GetMapping("/{id}")
    Dept get(@PathVariable Long id) {
        return deptService.get(id);
    }

    @Operation(summary = "创建")
    @PostMapping
    Dept create(@Valid @RequestBody DeptModel deptModel) {
        Dept dept = BeanUtils.newAndCopy(deptModel, Dept.class);
        return deptService.create(dept);
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    void update(@PathVariable Long id, @Valid @RequestBody DeptModel deptModel) {
        Dept user = BeanUtils.newAndCopy(deptModel, Dept.class);
        user.setId(id);
        deptService.update(user);
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        deptService.delete(id);
    }
}
