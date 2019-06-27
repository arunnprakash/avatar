package com.kirana.avatar.notification.repositories;

import org.springframework.stereotype.Repository;

import com.kirana.avatar.common.jpa.repository.BaseRepository;
import com.kirana.avatar.finance.model.NotificationType;

/**
 * @author __Telmila__
 *
 */
@Repository
public interface NotificationTypeRepository extends BaseRepository<NotificationType> {
}
