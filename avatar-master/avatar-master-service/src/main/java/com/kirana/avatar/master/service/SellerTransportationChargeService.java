/**
 * 
 */
package com.kirana.avatar.master.service;

import com.kirana.avatar.master.dto.SellerTransportationChargeDTO;
import com.kirana.avatar.common.service.BaseService;

/**
 * @author __Telmila__
 *
 */

public interface SellerTransportationChargeService extends BaseService<SellerTransportationChargeDTO>{

	public SellerTransportationChargeDTO getLatestSellerTransportationCharge();

}
