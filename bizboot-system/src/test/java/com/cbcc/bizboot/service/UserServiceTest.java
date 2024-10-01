package com.cbcc.bizboot.service;

import com.cbcc.bizboot.AbstractIntegrationTest;
import com.cbcc.bizboot.SqlCleanup;
import com.cbcc.bizboot.entity.Dept;
import com.cbcc.bizboot.entity.User;
import com.cbcc.bizboot.entity.dto.UpdatePasswordDTO;
import com.cbcc.bizboot.entity.dto.UpdateUserDTO;
import com.cbcc.bizboot.entity.dto.UserQueryDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

@SqlCleanup
public class UserServiceTest extends AbstractIntegrationTest {

    private final UserService userService;

    @Autowired
    public UserServiceTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    @Sql(scripts = "/sql/user/user-find-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testFind() {
        UserQueryDTO userQueryDTO = new UserQueryDTO();
        Page<User> users = userService.find(userQueryDTO, PageRequest.of(0, 20));
        Assertions.assertEquals(users.getTotalElements(), 2L);
    }

    @Test
    public void testCreate() {
        User user = new User();
        user.setUsername("user");
        user.setNickname("cat");
        user.setGender(0);
        user.setPhone("13411111111");
        user.setPassword("123456");
        user.setEnabled(true);
        user.setDept(new Dept(1L));
        User result = userService.create(user);
        Assertions.assertNotNull(result.getId());
    }

    @Test
    @Sql(scripts = "/sql/user/user-update-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testUpdate() {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setId(1L);
        updateUserDTO.setNickname("fish");
        updateUserDTO.setGender(0);
        updateUserDTO.setDeptId(1L);
        updateUserDTO.setPhone("13422222222");
        updateUserDTO.setEmail("123@test.com");
        updateUserDTO.setEnabled(true);
        userService.update(updateUserDTO);

        User user = userService.get(1L);
        Assertions.assertEquals(user.getNickname(), "fish");
        Assertions.assertEquals(user.getPassword(), "{noop}admin123");
    }

    @Test
    @Sql(scripts = "/sql/user/user-update-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testUpdateEnabled() {
        userService.updateEnabled(1L, false);
        User user = userService.get(1L);
        Assertions.assertFalse(user.getEnabled());
    }

    @Test
    @WithMockUser("cat")
    @Sql(scripts = "/sql/user/user-update-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testUpdatePassword() {
        UpdatePasswordDTO updatePasswordDTO = new UpdatePasswordDTO();
        updatePasswordDTO.setOldPwd("admin123");
        updatePasswordDTO.setNewPwd("admin456");
        userService.updatePassword(updatePasswordDTO);
    }
}
