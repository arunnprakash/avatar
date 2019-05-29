/**
 * 
 */
package com.kirana.avatar.master.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.kirana.avatar.common.feign.config.FeignClientContract;
import com.kirana.avatar.master.resource.CountryResource;
/**
 * @author __Telmila__
 *
 */
@FeignClient(name="country-service", configuration={FeignClientContract.class})
public interface CountryClient extends CountryResource {

}
