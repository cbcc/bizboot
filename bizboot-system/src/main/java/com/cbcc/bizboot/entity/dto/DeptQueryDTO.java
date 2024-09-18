package com.cbcc.bizboot.entity.dto;

import com.cbcc.bizboot.enums.DeptType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DeptQueryDTO {

    @Schema(title = "上级部门Id")
    private Long parentId;

    @Schema(title = "名称")
    private String name;

    /**
     * refs: {@link DeptType}
     */
    @Schema(title = "类型")
    private Integer type;

    @Schema(title = "排序")
    private Integer sort;

    @Schema(title = "是否启用")
    private Boolean enabled;

    @Schema(title = "备注")
    private String remark;
}
