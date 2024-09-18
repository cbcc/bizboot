package com.cbcc.bizboot.service.impl;

import com.cbcc.bizboot.entity.Dept;
import com.cbcc.bizboot.entity.User;
import com.cbcc.bizboot.entity.UserRole;
import com.cbcc.bizboot.entity.bo.UserInfo;
import com.cbcc.bizboot.entity.dto.UserQueryDTO;
import com.cbcc.bizboot.exception.BadRequestException;
import com.cbcc.bizboot.exception.ServiceException;
import com.cbcc.bizboot.repository.UserRepository;
import com.cbcc.bizboot.repository.UserRoleRepository;
import com.cbcc.bizboot.service.UserService;
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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserInfo getUserInfo(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new ServiceException("查询用户信息失败");
        }
        User user = optionalUser.get();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setUsername(user.getUsername());
        userInfo.setNickname(user.getNickname());
        userInfo.setGender(user.getGender());
        userInfo.setPhone(user.getPhone());
        userInfo.setEmail(user.getEmail());
        userInfo.setDeptName(user.getDept().getName());
        return userInfo;
    }

    @Override
    public Page<User> find(UserQueryDTO userQueryDTO, Pageable pageable) {
        User user = BeanUtils.newAndCopy(userQueryDTO, User.class);
        user.setDept(new Dept(userQueryDTO.getDeptId()));
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
