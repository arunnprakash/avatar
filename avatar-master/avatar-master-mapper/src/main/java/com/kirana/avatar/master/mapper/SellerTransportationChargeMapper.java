/**
 * 
 */
package com.kirana.avatar.master.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.master.dto.SellerTransportationChargeDTO;
import com.kirana.avatar.master.model.SellerTransportationCharge;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface SellerTransportationChargeMapper extends BaseMapper<SellerTransportationChargeDTO, SellerTransportationCharge>{

}
