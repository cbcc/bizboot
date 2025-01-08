package com.cbcc.bizboot.repository;

import com.cbcc.bizboot.entity.UserNotificationRead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserNotificationReadRepository extends JpaRepository<UserNotificationRead, Long> {

    boolean existsByUserIdAndNotificationId(long userId, long notificationId);
}
