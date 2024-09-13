package com.cbcc.bizboot.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "[username]不能为空")
    @Schema(title = "用户名")
    private String username;

    @NotBlank(message = "[password]不能为空")
    @Schema(title = "密码")
    private String password;
}
