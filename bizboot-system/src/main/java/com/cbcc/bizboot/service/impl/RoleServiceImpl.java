package com.cbcc.bizboot.service.impl;

import com.cbcc.bizboot.entity.Role;
import com.cbcc.bizboot.exception.BadRequestException;
import com.cbcc.bizboot.repository.RoleRepository;
import com.cbcc.bizboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<Role> find(Role role, Pageable pageable) {
        return roleRepository.findAll(Example.of(role), pageable);
    }

    @Override
    public Role get(long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format("角色不存在. id = {0}", id)));
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public void update(Role role) {
        Optional<Role> optionalRole = roleRepository.findById(role.getId());
        if (optionalRole.isEmpty()) {
            throw new BadRequestException(MessageFormat.format("角色不存在. id = {0}", role.getId()));
        }
        Role roleToUpdate = optionalRole.get();
        roleToUpdate.setName(role.getName());
        roleToUpdate.setCode(role.getCode());
        roleToUpdate.setEnabled(role.getEnabled());
        roleToUpdate.setRemark(role.getRemark());
        roleRepository.save(roleToUpdate);
    }

    @Override
    @Transactional
    public void updateEnabled(long id, boolean enabled) {
        boolean existed = roleRepository.existsById(id);
        if (!existed) {
            throw new BadRequestException(MessageFormat.format("角色不存在. id = {0}", id));
        }
        roleRepository.updateEnabledById(id, enabled);
    }

    @Override
    public void delete(long id) {
        roleRepository.deleteById(id);
    }
}
