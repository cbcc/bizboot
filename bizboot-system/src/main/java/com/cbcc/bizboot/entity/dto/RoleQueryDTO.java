package com.cbcc.bizboot.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RoleQueryDTO {

    @Schema(title = "名称")
    private String name;

    @Schema(title = "编码")
    private String code;

    @Schema(title = "是否启用")
    private Boolean enabled;

    @Schema(title = "备注")
    private String remark;
}
