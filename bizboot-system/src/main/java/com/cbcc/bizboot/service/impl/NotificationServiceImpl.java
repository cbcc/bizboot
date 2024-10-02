package com.cbcc.bizboot.service.impl;

import com.cbcc.bizboot.entity.Notification;
import com.cbcc.bizboot.entity.dto.NotificationQueryDTO;
import com.cbcc.bizboot.exception.BadRequestException;
import com.cbcc.bizboot.repository.NotificationRepository;
import com.cbcc.bizboot.service.NotificationService;
import com.cbcc.bizboot.util.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Page<Notification> find(NotificationQueryDTO notificationQueryDTO, Pageable pageable) {
        Notification notification = BeanUtils.newAndCopy(notificationQueryDTO, Notification.class);
        return notificationRepository.findAll(Example.of(notification), pageable);
    }

    @Override
    public Notification get(long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(MessageFormat.format("通知不存在. id = {0}", id)));
    }

    @Override
    public Notification create(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void update(Notification notification) {
        long id = notification.getId();
        Optional<Notification> optionalNotification = notificationRepository.findById(id);
        if (optionalNotification.isEmpty()) {
            throw new BadRequestException(MessageFormat.format("通知不存在. id = {0}", id));
        }
        Notification notificationToUpdate = optionalNotification.get();
        notificationToUpdate.setTitle(notification.getTitle());
        notificationToUpdate.setContext(notification.getContext());
        notificationToUpdate.setType(notification.getType());
        notificationToUpdate.setActive(notification.getActive());
        notificationRepository.save(notificationToUpdate);
    }

    @Override
    @Transactional
    public void updateActive(long id, boolean active) {
        boolean existed = notificationRepository.existsById(id);
        if (!existed) {
            throw new BadRequestException(MessageFormat.format("通知不存在. id = {0}", id));
        }
        notificationRepository.updateActiveById(id, active);
    }

    @Override
    public void delete(long id) {
        notificationRepository.deleteById(id);
    }
}
