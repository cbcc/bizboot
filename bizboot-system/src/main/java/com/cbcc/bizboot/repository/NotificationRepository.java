package com.cbcc.bizboot.repository;

import com.cbcc.bizboot.entity.Notification;
import com.cbcc.bizboot.entity.dto.NotificationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Modifying
    @Query("UPDATE Notification n SET n.active = :active WHERE n.id = :id")
    void updateActiveById(long id, boolean active);

    @Query("""
                SELECT new com.cbcc.bizboot.entity.dto.NotificationDTO(n, (u.id IS NOT NULL))
                FROM Notification n
                LEFT JOIN UserNotificationRead u
                    ON n.id = u.notificationId AND u.userId = :userId
                WHERE n.type = :type
                order by n.createdTime desc
                """)
    Page<NotificationDTO> findWithReadByUserIdAndTypeOrderByCreatedTimeDesc(long userId, int type, Pageable pageable);
}
