package com.cbcc.bizboot.service;

import com.cbcc.bizboot.entity.User;
import com.cbcc.bizboot.entity.bo.UserInfo;
import com.cbcc.bizboot.entity.dto.UpdatePasswordDTO;
import com.cbcc.bizboot.entity.dto.UpdateUserDTO;
import com.cbcc.bizboot.entity.dto.UserQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserInfo getUserInfo(String username);

    Page<User> find(UserQueryDTO userQueryDTO, Pageable pageable);

    User findByUsername(String username);

    User get(long id);

    User create(User user);

    void update(UpdateUserDTO updateUserDTO);

    void updateEnabled(long id, boolean enabled);

    List<Long> getRoleIds(long id);

    void updateRoles(long id, List<Long> roleIds);

    void delete(long id);

    void updatePassword(UpdatePasswordDTO updatePasswordDTO);
}
