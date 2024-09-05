package com.cbcc.bizboot.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RoleMenu extends BaseEntity {

    private Long roleId;

    private Long menuId;
}
