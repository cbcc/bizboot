package com.cbcc.bizboot.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Menu extends BaseEntity {

    @Schema(title = "上级菜单Id")
    private Long parentId;

    @Schema(title = "标题")
    private String title;

    @Schema(title = "路径")
    private String path;

    @Schema(title = "排序")
    private Integer sort;

    @Schema(title = "是否启用")
    private Boolean enabled;
}
