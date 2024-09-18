package com.cbcc.bizboot.entity.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoleModel {

    @NotBlank(message = "[name]不能为空")
    @Schema(title = "名称")
    private String name;

    @NotBlank(message = "[code]不能为空")
    @Schema(title = "编码")
    private String code;

    @NotNull(message = "[enabled]不能为空")
    @Schema(title = "是否启用")
    private Boolean enabled;

    @Schema(title = "备注")
    private String remark;
}
