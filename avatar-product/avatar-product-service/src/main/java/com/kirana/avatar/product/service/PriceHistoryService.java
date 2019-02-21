/**
 * 
 */
package com.kirana.avatar.product.service;

import com.kirana.avatar.common.dto.BaseDTO;
import com.kirana.avatar.common.dto.PagingAndFilterRequest;
import com.kirana.avatar.common.dto.PagingAndFilterResponse;
import com.kirana.avatar.common.service.BaseService;
import com.kirana.avatar.product.dto.PriceHistoryDTO;

/**
 * @author __Telmila__
 *
 */
public interface PriceHistoryService extends BaseService<PriceHistoryDTO>{

	PagingAndFilterResponse<BaseDTO> getProductsForUser(PagingAndFilterRequest pagingAndFilterRequest, Long userId, Long talukId, Long districtId, Long stateId);

}
