package com.cbcc.bizboot.entity.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleModel {

    @Size(max = 16, message = "[name]最大长度为16")
    @NotBlank(message = "[name]不能为空")
    @Schema(title = "名称")
    private String name;

    @Size(max = 16, message = "[code]最大长度为16")
    @NotBlank(message = "[code]不能为空")
    @Schema(title = "编码")
    private String code;

    @NotNull(message = "[enabled]不能为空")
    @Schema(title = "是否启用")
    private Boolean enabled;

    @Size(max = 50, message = "[remark]最大长度为50")
    @Schema(title = "备注")
    private String remark;
}
