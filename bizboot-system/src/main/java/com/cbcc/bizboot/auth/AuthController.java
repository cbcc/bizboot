package com.cbcc.bizboot.auth;

import com.cbcc.bizboot.auth.JwtUtils.TokenType;
import com.cbcc.bizboot.auth.dto.LoginRequest;
import com.cbcc.bizboot.auth.dto.LoginResult;
import com.cbcc.bizboot.auth.dto.RefreshRequest;
import com.cbcc.bizboot.auth.dto.RefreshResult;
import com.cbcc.bizboot.entity.bo.UserInfo;
import com.cbcc.bizboot.exception.BadRequestException;
import com.cbcc.bizboot.service.UserService;
import com.cbcc.bizboot.util.DateUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Date;

@Tag(name = "登录接口")
@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    public AuthController(UserService userService, AuthenticationManager authenticationManager,
                          JwtUtils jwtUtils) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Operation(summary = "登录")
    @PostMapping("/login")
    public LoginResult login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            // 生成未验证的身份凭证
            String username = loginRequest.getUsername();
            String password = new String(Base64.getDecoder().decode(loginRequest.getPassword()));
            UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken
                    .unauthenticated(username, password);
            // 身份验证
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            // 生成 token
            User user = (User) authentication.getPrincipal();
            Date expiration = jwtUtils.generateExpiration(TokenType.AccessToken);
            final String accessToken = jwtUtils.generateAccessToken(user.getUsername(), expiration);
            final String refreshToken = jwtUtils.generateRefreshToken(user.getUsername());

            UserInfo userInfo = userService.getUserInfo(username);
            LoginResult loginResult = new LoginResult();
            loginResult.setAccessToken(accessToken);
            loginResult.setRefreshToken(refreshToken);
            loginResult.setExpires(DateUtils.toLocalDateTime(expiration));
            loginResult.setUsername(userInfo.getUsername());
            loginResult.setNickname(userInfo.getNickname());
            loginResult.setGender(userInfo.getGender());
            loginResult.setPhone(userInfo.getPhone());
            loginResult.setEmail(userInfo.getEmail());
            loginResult.setDeptName(userInfo.getDeptName());
            return loginResult;

        } catch (AuthenticationException e) {
            throw new BadRequestException("用户名或密码错误");
        }
    }

    @Operation(summary = "刷新令牌")
    @PostMapping("/refresh-token")
    public RefreshResult refresh(@RequestBody @Valid RefreshRequest refreshRequest) {
        if (jwtUtils.validateRefreshToken(refreshRequest.getRefreshToken())) {
            String username = jwtUtils.extractUsername(refreshRequest.getRefreshToken(), TokenType.RefreshToken);
            Date expiration = jwtUtils.generateExpiration(TokenType.AccessToken);
            final String accessToken = jwtUtils.generateAccessToken(username, expiration);
            // 保持原 refreshToken
            return new RefreshResult(accessToken, refreshRequest.getRefreshToken(),
                    DateUtils.toLocalDateTime(expiration));

        } else {
            throw new BadRequestException("无效的刷新令牌");
        }
    }
}
