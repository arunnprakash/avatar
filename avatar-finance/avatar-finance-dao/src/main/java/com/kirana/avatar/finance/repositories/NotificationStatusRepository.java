package com.kirana.avatar.notification.repositories;

import org.springframework.stereotype.Repository;

import com.kirana.avatar.common.jpa.repository.BaseRepository;
import com.kirana.avatar.finance.model.NotificationStatus;

/**
 * @author __Telmila__
 *
 */
@Repository
public interface NotificationStatusRepository extends BaseRepository<NotificationStatus> {
}
