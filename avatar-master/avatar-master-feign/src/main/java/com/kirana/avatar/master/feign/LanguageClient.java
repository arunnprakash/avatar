/**
 * 
 */
package com.kirana.avatar.master.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.kirana.avatar.common.feign.config.FeignClientContract;
import com.kirana.avatar.master.resource.LanguageResource;
/**
 * @author __Telmila__
 *
 */
@FeignClient(name="language-service", configuration={FeignClientContract.class})
public interface LanguageClient extends LanguageResource {

}
