package com.kirana.avatar.offline.repositories;

import org.springframework.stereotype.Repository;

import com.kirana.avatar.offline.model.IncomingSms;
import com.kirana.avatar.common.jpa.repository.BaseRepository;

/**
 * @author __Telmila__
 *
 */
@Repository
public interface IncomingSmsRepository extends BaseRepository<IncomingSms> {
}
