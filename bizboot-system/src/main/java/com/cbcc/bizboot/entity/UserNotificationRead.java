package com.cbcc.bizboot.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserNotificationRead extends BaseEntity {

    @Schema(title = "用户Id")
    private Long userId;

    @Schema(title = "通知Id")
    private Long notificationId;
}
