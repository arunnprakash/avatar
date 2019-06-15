package com.kirana.avatar.offline.repositories;

import org.springframework.stereotype.Repository;

import com.kirana.avatar.offline.model.SmsProcessedStatus;
import com.kirana.avatar.common.jpa.repository.BaseRepository;

/**
 * @author __Telmila__
 *
 */
@Repository
public interface SmsProcessedStatusRepository extends BaseRepository<SmsProcessedStatus> {
}
