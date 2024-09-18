package com.cbcc.bizboot.entity.dto.model;

import com.cbcc.bizboot.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserModel {

    @NotBlank(message = "[username]不能为空")
    @Schema(title = "用户名")
    private String username;

    @Schema(title = "昵称")
    private String nickname;

    /**
     * refs: {@link Gender}
     */
    @Schema(title = "性别")
    private Integer gender;

    @NotNull(message = "[deptId]不能为空")
    @Schema(title = "部门Id")
    private Long deptId;

    @NotBlank(message = "[phone]不能为空")
    @Schema(title = "手机号")
    private String phone;

    @Schema(title = "邮箱")
    private String email;

    @NotBlank(message = "[password]不能为空")
    @Schema(title = "密码")
    private String password;

    @NotNull(message = "[enabled]不能为空")
    @Schema(title = "是否启用")
    private Boolean enabled;
}