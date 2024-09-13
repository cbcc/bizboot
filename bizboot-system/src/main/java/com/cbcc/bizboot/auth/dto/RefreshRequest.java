package com.cbcc.bizboot.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshRequest {

    @NotBlank(message = "[refreshToken]不能为空")
    @Schema(title = "刷新令牌")
    private String refreshToken;
}
