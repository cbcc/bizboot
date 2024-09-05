package com.cbcc.bizboot.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(title = "Id")
    private Long id;

    @CreatedDate
    @Column(updatable = false)
    @Schema(title = "创建时间")
    private LocalDateTime createdTime;

    @CreatedBy
    @Column(updatable = false)
    @Schema(title = "创建人账号")
    private String createdBy;

    @LastModifiedDate
    @Schema(title = "最后修改时间")
    private LocalDateTime lastModifiedTime;

    @LastModifiedBy
    @Schema(title = "最后修改人账号")
    private String lastModifiedBy;

    public BaseEntity(Long id) {
        this.id = id;
    }
}
