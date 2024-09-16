package com.cbcc.bizboot.service;

import com.cbcc.bizboot.entity.User;
import com.cbcc.bizboot.entity.bo.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserInfo getUserInfo(String username);

    Page<User> find(User user, Pageable pageable);

    User findByUsername(String username);

    User get(long id);

    User create(User user);

    void update(User user);

    void updateEnabled(long id, boolean enabled);

    List<Long> getRoleIds(long id);

    void updateRoles(long id, List<Long> roleIds);

    void delete(long id);
}
