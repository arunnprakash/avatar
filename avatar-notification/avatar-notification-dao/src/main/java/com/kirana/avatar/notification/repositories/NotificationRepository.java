package com.kirana.avatar.notification.repositories;

import org.springframework.stereotype.Repository;

import com.kirana.avatar.notification.model.Notification;
import com.kirana.avatar.common.jpa.repository.BaseRepository;

/**
 * @author __Telmila__
 *
 */
@Repository
public interface NotificationRepository extends BaseRepository<Notification> {
}
