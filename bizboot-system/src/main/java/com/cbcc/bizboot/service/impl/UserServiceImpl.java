package com.cbcc.bizboot.service.impl;

import com.cbcc.bizboot.entity.User;
import com.cbcc.bizboot.entity.UserRole;
import com.cbcc.bizboot.exception.BadRequestException;
import com.cbcc.bizboot.repository.UserRepository;
import com.cbcc.bizboot.repository.UserRoleRepository;
import com.cbcc.bizboot.service.UserService;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public Page<User> find(User user, Pageable pageable) {
        return userRepository.findAll(Example.of(user), pageable);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format("用户不存在. username = {0}", username)));
    }

    @Override
    public User get(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format("用户不存在. id = {0}", id)));
    }

    @Override
    public User create(User user) {
        // todo: 改进生成 uid 方式
        String uid = UUID.randomUUID().toString().replace("-", "");
        user.setUid(uid);
        return userRepository.save(user);
    }

    @Override
    public void update(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isEmpty()) {
            throw new BadRequestException(MessageFormat.format("用户不存在. id = {0}", user.getId()));
        }
        User userToUpdate = optionalUser.get();
        userToUpdate.setNickname(user.getNickname());
        userToUpdate.setGender(user.getGender());
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setEnabled(user.getEnabled());
        userToUpdate.setDept(user.getDept());
        userRepository.save(userToUpdate);
    }

    @Override
    @Transactional
    public void updateEnabled(long id, boolean enabled) {
        boolean existed = userRepository.existsById(id);
        if (!existed) {
            throw new BadRequestException(MessageFormat.format("用户不存在. id = {0}", id));
        }
        userRepository.updateEnabledById(id, enabled);
    }

    @Override
    public List<Long> getRoleIds(long id) {
        List<UserRole> userRoles = userRoleRepository.findByUserId(id);
        return userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateRoles(long id, List<Long> roleIds) {
        boolean existed = userRepository.existsById(id);
        if (!existed) {
            throw new BadRequestException(MessageFormat.format("用户不存在. id = {0}", id));
        }
        List<UserRole> existedUserRoles = userRoleRepository.findByUserId(id);
        Set<Long> existedRoleIds =
                existedUserRoles.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
        Set<Long> toGrantRoleIds = Sets.difference(Sets.newHashSet(roleIds), existedRoleIds);

        List<UserRole> toCreate = toGrantRoleIds.stream().map(it -> new UserRole(id, it))
                .collect(Collectors.toList());
        List<UserRole> toDelete = existedUserRoles.stream().filter(it -> !roleIds.contains(it.getRoleId()))
                .collect(Collectors.toList());

        userRoleRepository.deleteAll(toDelete);
        userRoleRepository.saveAll(toCreate);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
