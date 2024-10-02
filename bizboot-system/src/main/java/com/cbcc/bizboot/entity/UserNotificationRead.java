package com.cbcc.bizboot.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserNotificationRead extends BaseEntity {

    @Schema(title = "用户Id")
    private Long userId;

    @Schema(title = "通知Id")
    private Long notificationId;
}
