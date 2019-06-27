package com.kirana.avatar.finance.repositories;

import org.springframework.stereotype.Repository;

import com.kirana.avatar.common.jpa.repository.BaseRepository;
import com.kirana.avatar.finance.model.Wallet;

/**
 * @author __Telmila__
 *
 */
@Repository
public interface WalletRepository extends BaseRepository<Wallet> {
}
