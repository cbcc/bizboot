package com.cbcc.bizboot.entity;

import com.cbcc.bizboot.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@NamedEntityGraph(
        name = "User.dept",
        attributeNodes = @NamedAttributeNode("dept")
)
public class User extends BaseEntity {

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

    @Schema(title = "密码")
    private String password;

    @Schema(title = "是否启用")
    private Boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @Schema(title = "部门")
    private Dept dept;
}