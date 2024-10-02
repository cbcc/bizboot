package com.cbcc.bizboot.controller;

import com.cbcc.bizboot.entity.Notification;
import com.cbcc.bizboot.entity.dto.NotificationQueryDTO;
import com.cbcc.bizboot.entity.dto.model.ActiveModel;
import com.cbcc.bizboot.entity.dto.model.NotificationModel;
import com.cbcc.bizboot.service.NotificationService;
import com.cbcc.bizboot.util.BeanUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "通知公告接口")
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Operation(summary = "分页查询")
    @GetMapping
    PagedModel<Notification> find(NotificationQueryDTO notificationQueryDTO, Pageable pageable) {
        return new PagedModel<>(notificationService.find(notificationQueryDTO, pageable));
    }

    @Operation(summary = "查询")
    @GetMapping("/{id}")
    Notification get(@PathVariable Long id) {
        return notificationService.get(id);
    }

    @Operation(summary = "创建")
    @PostMapping
    Notification create(@Valid @RequestBody NotificationModel model) {
        Notification notification = BeanUtils.newAndCopy(model, Notification.class);
        return notificationService.create(notification);
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    void update(@PathVariable Long id, @Valid @RequestBody NotificationModel model) {
        Notification notification = BeanUtils.newAndCopy(model, Notification.class);
        notification.setId(id);
        notificationService.update(notification);
    }

    @Operation(summary = "修改是否生效状态")
    @PatchMapping("/{id}/active")
    void updateActive(@PathVariable Long id, @Valid @RequestBody ActiveModel model) {
        notificationService.updateActive(id, model.getActive());
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        notificationService.delete(id);
    }
}
