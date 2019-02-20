/**
 * 
 */
package com.kirana.avatar.transaction.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.transaction.dto.SellerTransactionDTO;
import com.kirana.avatar.transaction.model.SellerTransaction;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface SellerTransactionMapper extends BaseMapper<SellerTransactionDTO, SellerTransaction>{

}
