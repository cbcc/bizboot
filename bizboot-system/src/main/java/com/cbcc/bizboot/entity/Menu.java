package com.cbcc.bizboot.entity;

import com.cbcc.bizboot.enums.MenuType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Menu extends BaseEntity {

    /**
     * refs: {@link MenuType}
     */
    @Schema(title = "类型")
    private Integer type;

    @Schema(title = "上级菜单Id")
    private Long parentId;

    @Schema(title = "标题")
    private String title;

    @Schema(title = "路由名称")
    private String name;

    @Schema(title = "路由路径")
    private String path;

    @Schema(title = "组件路径")
    private String component;

    @Schema(title = "图标")
    private String icon;

    @Schema(title = "右侧图标")
    private String extraIcon;

    @Schema(title = "进场动画")
    private String enterTransition;

    @Schema(title = "离场动画")
    private String leaveTransition;

    @Schema(title = "菜单激活")
    private String activePath;

    @Schema(title = "路由重定向")
    private String redirect;

    @Schema(title = "按钮权限标识")
    private String auths;

    @Schema(title = "iframe 链接地址")
    private String frameSrc;

    @Schema(title = "iframe 是否加载动画")
    private Boolean frameLoading;

    @Schema(title = "是否缓存页面")
    private Boolean keepAlive;

    @Schema(title = "是否显示标签页")
    private Boolean hiddenTag;

    @Schema(title = "是否固定标签页")
    private Boolean fixedTag;

    @Schema(title = "是否显示该菜单")
    private Boolean showLink;

    @Schema(title = "是否显示父级菜单")
    private Boolean showParent;

    @Schema(title = "排序")
    private Integer sort;
}
