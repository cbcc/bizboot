package com.cbcc.bizboot.auth.dto;

import com.cbcc.bizboot.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginResult {

    @Schema(title = "访问令牌")
    private String accessToken;

    @Schema(title = "刷新令牌")
    private String refreshToken;

    @Schema(title = "过期时间")
    private LocalDateTime expires;

    @Schema(title = "用户名")
    private String username;

    @Schema(title = "昵称")
    private String nickname;

    /**
     * refs: {@link Gender}
     */
    @Schema(title = "性别")
    private Integer gender;

    @Schema(title = "手机号")
    private String phone;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "部门名称")
    private String deptName;
}
