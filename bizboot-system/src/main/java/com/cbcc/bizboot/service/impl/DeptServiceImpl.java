package com.cbcc.bizboot.service.impl;

import com.cbcc.bizboot.entity.Dept;
import com.cbcc.bizboot.entity.dto.DeptQueryDTO;
import com.cbcc.bizboot.exception.BadRequestException;
import com.cbcc.bizboot.repository.DeptRepository;
import com.cbcc.bizboot.service.DeptService;
import com.cbcc.bizboot.util.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class DeptServiceImpl implements DeptService {

    private final DeptRepository deptRepository;

    public DeptServiceImpl(DeptRepository deptRepository) {
        this.deptRepository = deptRepository;
    }

    @Override
    public Page<Dept> find(DeptQueryDTO deptQueryDTO, Pageable pageable) {
        Dept dept = BeanUtils.newAndCopy(deptQueryDTO, Dept.class);
        return deptRepository.findAll(Example.of(dept), pageable);
    }

    @Override
    public Dept get(long id) {
        return deptRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format("用户不存在. id = {0}", id)));
    }

    @Override
    public Dept create(Dept dept) {
        return deptRepository.save(dept);
    }

    @Override
    public void update(Dept dept) {
        Optional<Dept> optionalDept = deptRepository.findById(dept.getId());
        if (optionalDept.isEmpty()) {
            throw new BadRequestException(MessageFormat.format("部门不存在. id = {0}", dept.getId()));
        }
        Dept deptToUpdate = optionalDept.get();
        deptToUpdate.setParentId(dept.getParentId());
        deptToUpdate.setName(dept.getName());
        deptToUpdate.setSort(dept.getSort());
        deptToUpdate.setEnabled(dept.getEnabled());
        deptToUpdate.setRemark(dept.getRemark());
        deptRepository.save(deptToUpdate);
    }

    @Override
    public void delete(long id) {
        deptRepository.deleteById(id);
    }
}
