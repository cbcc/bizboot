package com.cbcc.bizboot.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RefreshResult {

    @Schema(title = "访问令牌")
    private String accessToken;

    @Schema(title = "刷新令牌")
    private String refreshToken;

    @Schema(title = "过期时间")
    private LocalDateTime expires;
}
