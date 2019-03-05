/**
 * 
 */
package com.kirana.avatar.authorization.service;

import com.kirana.avatar.authorization.dto.QcWareHouseMappingDTO;
import com.kirana.avatar.common.service.BaseService;

/**
 * @author __Telmila__
 *
 */

public interface QcWareHouseMappingService extends BaseService<QcWareHouseMappingDTO>{

	public QcWareHouseMappingDTO findByQcId(Long qcId);

}
