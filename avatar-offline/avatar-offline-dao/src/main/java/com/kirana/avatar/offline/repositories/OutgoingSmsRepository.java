package com.kirana.avatar.offline.repositories;

import org.springframework.stereotype.Repository;

import com.kirana.avatar.offline.model.OutgoingSms;
import com.kirana.avatar.common.jpa.repository.BaseRepository;

/**
 * @author __Telmila__
 *
 */
@Repository
public interface OutgoingSmsRepository extends BaseRepository<OutgoingSms> {
}
