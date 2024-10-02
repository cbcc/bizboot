package com.cbcc.bizboot.repository;

import com.cbcc.bizboot.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Modifying
    @Query("UPDATE Notification n SET n.active = :active WHERE n.id = :id")
    void updateActiveById(long id, boolean active);
}
