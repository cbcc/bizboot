package com.cbcc.bizboot.entity.dto;

import com.cbcc.bizboot.entity.Notification;
import com.cbcc.bizboot.enums.NotificationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NotificationDTO {

    @Schema(title = "标题")
    private String title;

    @Schema(title = "内容")
    private String context;

    /**
     * refs: {@link NotificationType}
     */
    @Schema(title = "类型")
    private Integer type;

    @Schema(title = "创建时间")
    private LocalDateTime createdTime;

    @Schema(title = "是否已读")
    private Boolean read;

    public NotificationDTO(Notification notification, Boolean read) {
        this.title = notification.getTitle();
        this.context = notification.getContext();
        this.type = notification.getType();
        this.createdTime = notification.getCreatedTime();
        this.read = read;
    }
}
