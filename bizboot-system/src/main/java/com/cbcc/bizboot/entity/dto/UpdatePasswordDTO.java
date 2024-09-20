package com.cbcc.bizboot.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePasswordDTO {

    @NotBlank(message = "[oldPwd]不能为空")
    @Schema(title = "旧密码")
    private String oldPwd;

    @NotBlank(message = "[newPwd]不能为空")
    @Schema(title = "新密码")
    private String newPwd;
}
