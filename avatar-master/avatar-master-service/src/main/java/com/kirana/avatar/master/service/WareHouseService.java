/**
 * 
 */
package com.kirana.avatar.master.service;

import com.kirana.avatar.master.dto.WareHouseDTO;

import java.util.List;

import com.kirana.avatar.common.service.BaseService;

/**
 * @author __Telmila__
 *
 */

public interface WareHouseService extends BaseService<WareHouseDTO>{

	public List<WareHouseDTO> getWareHousesByMarket(Long marketId);

}
