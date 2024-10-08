package com.cbcc.bizboot.entity.bo;

import com.cbcc.bizboot.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserInfo {

    private Long id;

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
