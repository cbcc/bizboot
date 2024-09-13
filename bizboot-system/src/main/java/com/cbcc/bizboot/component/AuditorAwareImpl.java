package com.cbcc.bizboot.component;

import com.cbcc.bizboot.entity.bo.UserInfo;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    private final UserInfoHolder userInfoHolder;

    public AuditorAwareImpl(UserInfoHolder userInfoHolder) {
        this.userInfoHolder = userInfoHolder;
    }

    @Nonnull
    @Override
    public Optional<String> getCurrentAuditor() {
        UserInfo userInfo = userInfoHolder.getUserInfo();
        return Optional.of(userInfo.getUsername());
    }
}
