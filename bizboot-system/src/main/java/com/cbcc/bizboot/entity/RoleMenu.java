package com.cbcc.bizboot.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RoleMenu extends BaseEntity {

    @Schema(title = "角色Id")
    private Long roleId;

    @Schema(title = "菜单Id")
    private Long menuId;
}
