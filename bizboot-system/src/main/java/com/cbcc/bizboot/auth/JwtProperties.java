package com.cbcc.bizboot.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(
        prefix = "bizboot.jwt"
)
public class JwtProperties {

    private TokenProperties accessToken;

    private TokenProperties refreshToken;

    @Getter
    @Setter
    public static class TokenProperties {
        private String secretKey;
        private long duration;
    }
}
