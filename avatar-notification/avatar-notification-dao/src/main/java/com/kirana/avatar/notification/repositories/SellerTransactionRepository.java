package com.kirana.avatar.transaction.repositories;

import org.springframework.stereotype.Repository;

import com.kirana.avatar.transaction.model.SellerTransaction;
import com.kirana.avatar.common.jpa.repository.BaseRepository;

/**
 * @author __Telmila__
 *
 */
@Repository
public interface SellerTransactionRepository extends BaseRepository<SellerTransaction> {
}
