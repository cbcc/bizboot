package com.cbcc.bizboot.entity.dto;

import com.cbcc.bizboot.enums.MenuType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MenuQueryDTO {

    /**
     * refs: {@link MenuType}
     */
    @Schema(title = "类型")
    private Integer type;

    @Schema(title = "标题")
    private String title;

    @Schema(title = "路由名称")
    private String name;

    @Schema(title = "路由路径")
    private String path;

    @Schema(title = "组件路径")
    private String component;
}
