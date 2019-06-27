/**
 * 
 */
package com.kirana.avatar.finance.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.finance.dto.WalletDTO;
import com.kirana.avatar.common.mapper.BaseMapper;
import com.kirana.avatar.finance.model.Wallet;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface WalletMapper extends BaseMapper<WalletDTO, Wallet>{

}
