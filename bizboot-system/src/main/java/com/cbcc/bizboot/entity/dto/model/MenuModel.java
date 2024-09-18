package com.cbcc.bizboot.entity.dto.model;

import com.cbcc.bizboot.enums.MenuType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MenuModel {

    /**
     * refs: {@link MenuType}
     */
    @Min(value = 0, message = "[type]不合法")
    @Max(value = 3, message = "[type]不合法")
    @NotNull(message = "[type]不能为空")
    @Schema(title = "类型")
    private Integer type;

    @Schema(title = "上级菜单Id")
    private Long parentId;

    @Size(max = 16, message = "[title]最大长度为16")
    @NotBlank(message = "[title]不能为空")
    @Schema(title = "标题")
    private String title;

    @Size(max = 30, message = "[name]最大长度为30")
    @Schema(title = "路由名称")
    private String name;

    @Size(max = 250, message = "[path]最大长度为250")
    @Schema(title = "路由路径")
    private String path;

    @Size(max = 250, message = "[component]最大长度为250")
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

    @Size(max = 250, message = "[activePath]最大长度为250")
    @Schema(title = "菜单激活")
    private String activePath;

    @Size(max = 250, message = "[redirect]最大长度为250")
    @Schema(title = "路由重定向")
    private String redirect;

    @Size(max = 30, message = "[auths]最大长度为30")
    @Schema(title = "按钮权限标识")
    private String auths;

    @Size(max = 250, message = "[frameSrc]最大长度为250")
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

    @Positive(message = "[sort]必须大于0")
    @Schema(title = "排序")
    private Integer sort;
}
