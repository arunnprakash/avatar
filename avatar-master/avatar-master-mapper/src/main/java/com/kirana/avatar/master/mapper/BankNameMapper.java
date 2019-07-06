/**
 * 
 */
package com.kirana.avatar.master.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.master.dto.BankNameDTO;
import com.kirana.avatar.master.model.BankName;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface BankNameMapper extends BaseMapper<BankNameDTO, BankName>{

}
