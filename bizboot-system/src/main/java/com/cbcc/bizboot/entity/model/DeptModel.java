package com.cbcc.bizboot.entity.model;

import com.cbcc.bizboot.enums.DeptType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeptModel {

    @Schema(title = "上级部门Id")
    private Long parentId;

    @NotBlank(message = "[name]不能为空")
    @Schema(title = "名称")
    private String name;

    /**
     * refs: {@link DeptType}
     */
    @NotNull(message = "[type]不能为空")
    @Schema(title = "类型")
    private Integer type;

    @Schema(title = "排序")
    private Integer sort;

    @NotNull(message = "[enabled]不能为空")
    @Schema(title = "是否启用")
    private Boolean enabled;

    @Schema(title = "备注")
    private String remark;
}
