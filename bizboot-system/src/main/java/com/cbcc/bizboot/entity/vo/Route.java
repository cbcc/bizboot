package com.cbcc.bizboot.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Route {

    @Schema(title = "路由名称")
    private String name;

    @Schema(title = "路由路径")
    private String path;

    @Schema(title = "组件路径")
    private String component;

    @Schema(title = "元数据")
    private Meta meta;

    @Schema(title = "子路由")
    private List<Route> children;

    @Data
    public static class Meta {
        @Schema(title = "标题")
        private String title;

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
        private Integer rank;

        @Schema(title = "权限标识列表")
        private List<String> auths;

        public void addAuth(String auth) {
            if (this.auths == null) {
                this.auths = new ArrayList<>();
            }
            this.auths.add(auth);
        }
    }

    public void addChild(Route route) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(route);
    }
}
