package com.cbcc.bizboot.service.impl;

import com.cbcc.bizboot.entity.Role;
import com.cbcc.bizboot.entity.RoleMenu;
import com.cbcc.bizboot.entity.dto.RoleQueryDTO;
import com.cbcc.bizboot.exception.BadRequestException;
import com.cbcc.bizboot.repository.RoleMenuRepository;
import com.cbcc.bizboot.repository.RoleRepository;
import com.cbcc.bizboot.service.RoleService;
import com.cbcc.bizboot.util.BeanUtils;
import com.google.common.collect.Sets;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final RoleMenuRepository roleMenuRepository;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMenuRepository roleMenuRepository) {
        this.roleRepository = roleRepository;
        this.roleMenuRepository = roleMenuRepository;
    }

    @Override
    public Page<Role> find(RoleQueryDTO roleQueryDTO, Pageable pageable) {
        Role role = BeanUtils.newAndCopy(roleQueryDTO, Role.class);
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
    public List<Long> getMenuIds(long id) {
        List<RoleMenu> roleMenus = roleMenuRepository.findByRoleId(id);
        return roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateMenus(long id, List<Long> menuIds) {
        boolean existed = roleRepository.existsById(id);
        if (!existed) {
            throw new BadRequestException(MessageFormat.format("角色不存在. id = {0}", id));
        }
        List<RoleMenu> existedRoleMenus = roleMenuRepository.findByRoleId(id);
        Set<Long> existedMenuIds =
                existedRoleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toSet());
        Set<Long> toGrantMenuIds = Sets.difference(Sets.newHashSet(menuIds), existedMenuIds);

        List<RoleMenu> toCreate = toGrantMenuIds.stream().map(it -> new RoleMenu(id, it))
                .collect(Collectors.toList());
        List<RoleMenu> toDelete = existedRoleMenus.stream().filter(it -> !menuIds.contains(it.getMenuId()))
                .collect(Collectors.toList());

        roleMenuRepository.deleteAll(toDelete);
        roleMenuRepository.saveAll(toCreate);
    }

    @Override
    public void delete(long id) {
        roleRepository.deleteById(id);
    }
}
