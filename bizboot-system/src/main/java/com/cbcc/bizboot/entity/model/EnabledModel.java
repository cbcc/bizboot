package com.cbcc.bizboot.entity.model;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnabledModel {

    @NotNull(message = "[enabled]不能为空")
    private Boolean enabled;
}
