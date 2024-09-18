package com.cbcc.bizboot.entity.dto.model;

import com.cbcc.bizboot.enums.DeptType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DeptModel {

    @Schema(title = "上级部门Id")
    private Long parentId;

    @Size(max = 30, message = "[name]最大长度为30")
    @NotBlank(message = "[name]不能为空")
    @Schema(title = "名称")
    private String name;

    /**
     * refs: {@link DeptType}
     */
    @Min(value = 0, message = "[type]不合法")
    @Max(value = 2, message = "[type]不合法")
    @NotNull(message = "[type]不能为空")
    @Schema(title = "类型")
    private Integer type;

    @PositiveOrZero(message = "[sort]必须大于或等于0")
    @Schema(title = "排序")
    private Integer sort;

    @NotNull(message = "[enabled]不能为空")
    @Schema(title = "是否启用")
    private Boolean enabled;

    @Size(max = 200, message = "[remark]最大长度为200")
    @Schema(title = "备注")
    private String remark;
}
