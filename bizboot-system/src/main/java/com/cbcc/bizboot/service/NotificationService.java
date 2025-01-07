package com.cbcc.bizboot.service;

import com.cbcc.bizboot.entity.Notification;
import com.cbcc.bizboot.entity.dto.NotificationDTO;
import com.cbcc.bizboot.entity.dto.NotificationQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService {

    Page<Notification> find(NotificationQueryDTO notificationQueryDTO, Pageable pageable);

    Notification get(long id);

    Page<NotificationDTO> getByCurrentUser(int type, Pageable pageable);

    Notification create(Notification notification);

    void update(Notification notification);

    void updateActive(long id, boolean active);

    void delete(long id);
}
