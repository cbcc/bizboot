package com.cbcc.bizboot.entity.dto.model;

import com.cbcc.bizboot.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserModel {

    @Size(max = 16, message = "[username]最大长度为16")
    @NotBlank(message = "[username]不能为空")
    @Schema(title = "用户名")
    private String username;

    @Size(max = 16, message = "[nickname]最大长度为16")
    @Schema(title = "昵称")
    private String nickname;

    /**
     * refs: {@link Gender}
     */
    @Min(value = 0, message = "[gender]不合法")
    @Max(value = 1, message = "[gender]不合法")
    @Schema(title = "性别")
    private Integer gender;

    @NotNull(message = "[deptId]不能为空")
    @Schema(title = "部门Id")
    private Long deptId;

    @Size(max = 16, message = "[phone]最大长度为16")
    @NotBlank(message = "[phone]不能为空")
    @Schema(title = "手机号")
    private String phone;

    @Size(max = 60, message = "[email]最大长度为60")
    @Schema(title = "邮箱")
    private String email;

    @NotBlank(message = "[password]不能为空")
    @Schema(title = "密码")
    private String password;

    @NotNull(message = "[enabled]不能为空")
    @Schema(title = "是否启用")
    private Boolean enabled;
}