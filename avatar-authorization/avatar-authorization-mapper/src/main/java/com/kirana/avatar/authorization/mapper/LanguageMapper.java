/**
 * 
 */
package com.kirana.avatar.authorization.mapper;

import org.mapstruct.Mapper;

import com.kirana.avatar.authorization.dto.LanguageDTO;
import com.kirana.avatar.authorization.model.Language;
import com.kirana.avatar.common.mapper.BaseMapper;

/**
 * @author __Telmila__
 *
 */

@Mapper(componentModel="spring")
public interface LanguageMapper extends BaseMapper<LanguageDTO, Language> {

}
