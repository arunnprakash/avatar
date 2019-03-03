/**
 * 
 */
package com.kirana.avatar.product.repositories;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kirana.avatar.common.jpa.repository.BaseRepository;
import com.kirana.avatar.product.model.PriceHistory;

/**
 * @author __Telmila__
 *
 */
@Repository
public interface PriceHistoryRepository extends BaseRepository<PriceHistory> {
	@Query("select priceHistory from PriceHistory priceHistory where "
			+ "priceHistory.product.id =:productId AND "
			+ "priceHistory.quality.id = 1 AND "
			+ "priceHistory.deleted = false "
			+ "ORDER BY priceHistory.lastModifiedDate DESC")
	public List<PriceHistory> getLatestPrice(Long productId);
	
	@Query("select priceHistory from PriceHistory priceHistory where "
			+ "priceHistory.product.id =:productId AND "
			+ "priceHistory.quality.id = :qualityId AND "
			+ "priceHistory.createdDate <= :createdDate AND "
			+ "priceHistory.deleted = false "
			+ "ORDER BY priceHistory.lastModifiedDate DESC")
	public List<PriceHistory> getLatestPrice(Long productId, Long qualityId, ZonedDateTime createdDate);
}
