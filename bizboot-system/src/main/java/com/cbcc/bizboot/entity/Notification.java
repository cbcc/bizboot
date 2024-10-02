package com.cbcc.bizboot.entity;

import com.cbcc.bizboot.enums.NotificationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Notification extends BaseEntity {

    @Schema(title = "标题")
    private String title;

    @Schema(title = "内容")
    private String context;

    /**
     * refs: {@link NotificationType}
     */
    @Schema(title = "类型")
    private Integer type;

    @Schema(title = "是否生效")
    private Boolean active;
}
