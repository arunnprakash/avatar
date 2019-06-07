/**
 * 
 */
package com.kirana.avatar.master.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.master.dto.SellerAgentCommissionDTO;
import com.kirana.avatar.master.model.SellerAgentCommission;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface SellerAgentCommissionMapper extends BaseMapper<SellerAgentCommissionDTO, SellerAgentCommission>{

}
