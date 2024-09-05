package com.cbcc.bizboot.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role extends BaseEntity {

    @Schema(title = "名称")
    private String name;

    @Schema(title = "编码")
    private String code;

    @Schema(title = "是否启用")
    private Boolean enabled;

    @Schema(title = "备注")
    private String remark;
}
