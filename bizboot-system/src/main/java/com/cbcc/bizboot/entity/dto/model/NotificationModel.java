package com.cbcc.bizboot.entity.dto.model;

import com.cbcc.bizboot.enums.NotificationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NotificationModel {

    @Size(max = 50, message = "[title]最大长度为50")
    @NotBlank(message = "[title]不能为空")
    @Schema(title = "标题")
    private String title;

    @Size(max = 2000, message = "[context]最大长度为2000")
    @NotBlank(message = "[context]不能为空")
    @Schema(title = "内容")
    private String context;

    /**
     * refs: {@link NotificationType}
     */
    @Min(value = 0, message = "[type]不合法")
    @Max(value = 1, message = "[type]不合法")
    @NotNull(message = "[type]不能为空")
    @Schema(title = "类型")
    private Integer type;

    @NotNull(message = "[active]不能为空")
    @Schema(title = "是否生效")
    private Boolean active;
}
