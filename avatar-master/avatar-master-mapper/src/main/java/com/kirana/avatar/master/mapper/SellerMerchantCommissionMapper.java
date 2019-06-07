/**
 * 
 */
package com.kirana.avatar.master.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.master.dto.SellerMerchantCommissionDTO;
import com.kirana.avatar.master.model.SellerMerchantCommission;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface SellerMerchantCommissionMapper extends BaseMapper<SellerMerchantCommissionDTO, SellerMerchantCommission>{

}
