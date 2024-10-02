package com.cbcc.bizboot.entity.dto.model;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActiveModel {

    @NotNull(message = "[active]不能为空")
    private Boolean active;
}
