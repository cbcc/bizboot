package com.cbcc.bizboot.service;

import com.cbcc.bizboot.AbstractIntegrationTest;
import com.cbcc.bizboot.SqlCleanup;
import com.cbcc.bizboot.entity.Notification;
import com.cbcc.bizboot.entity.dto.NotificationQueryDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

@SqlCleanup
public class NotificationServiceTest extends AbstractIntegrationTest {

    private final NotificationService notificationService;

    @Autowired
    public NotificationServiceTest(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Test
    @Sql(scripts = "/sql/notification/notification-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testFind() {
        NotificationQueryDTO queryDTO = new NotificationQueryDTO();
        Page<Notification> notifications = notificationService.find(queryDTO, PageRequest.of(0, 20));
        Assertions.assertEquals(notifications.getTotalElements(), 2L);
    }

    @Test
    public void testCreate() {
        Notification notification = new Notification();
        notification.setTitle("test");
        notification.setContext("context");
        notification.setType(0);
        notification.setActive(true);
        Notification result = notificationService.create(notification);
        Assertions.assertNotNull(result.getId());
    }

    @Test
    @Sql(scripts = "/sql/notification/notification-update-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testUpdate() {
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setTitle("new title");
        notification.setContext("new context");
        notification.setType(0);
        notification.setActive(true);
        notificationService.update(notification);

        Notification result = notificationService.get(1L);
        Assertions.assertEquals(result.getTitle(), "new title");
        Assertions.assertEquals(result.getContext(), "new context");
    }

    @Test
    @Sql(scripts = "/sql/notification/notification-update-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testUpdateActive() {
        notificationService.updateActive(1L, false);
        Notification notification = notificationService.get(1L);
        Assertions.assertFalse(notification.getActive());
    }
}
