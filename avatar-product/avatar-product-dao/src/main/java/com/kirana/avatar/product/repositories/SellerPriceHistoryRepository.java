/**
 * 
 */
package com.kirana.avatar.product.repositories;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kirana.avatar.common.jpa.repository.BaseRepository;
import com.kirana.avatar.product.model.SellerPriceHistory;

/**
 * @author __Telmila__
 *
 */
@Repository
public interface SellerPriceHistoryRepository extends BaseRepository<SellerPriceHistory> {
	@Query("select priceHistory from SellerPriceHistory priceHistory where "
			+ "priceHistory.product.id =:productId AND "
			+ "priceHistory.quality.id = 1 AND "
			+ "priceHistory.deleted = false "
			+ "ORDER BY priceHistory.lastModifiedDate DESC")
	public List<SellerPriceHistory> getLatestPrice(Long productId);
	
	@Query("select priceHistory from SellerPriceHistory priceHistory where "
			+ "priceHistory.product.id =:productId AND "
			+ "priceHistory.quality.id = :qualityId AND "
			+ "priceHistory.createdDate <= :createdDate AND "
			+ "priceHistory.deleted = false "
			+ "ORDER BY priceHistory.lastModifiedDate DESC")
	public List<SellerPriceHistory> getLatestPrice(Long productId, Long qualityId, ZonedDateTime createdDate);
}
