package com.cbcc.bizboot.entity.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MenuModel {

    @Schema(title = "上级菜单Id")
    private Long parentId;

    @NotBlank(message = "[title]不能为空")
    @Schema(title = "标题")
    private String title;

    @NotBlank(message = "[path]不能为空")
    @Schema(title = "路径")
    private String path;

    @Schema(title = "排序")
    private Integer sort;

    @NotNull(message = "[enabled]不能为空")
    @Schema(title = "是否启用")
    private Boolean enabled;
}
