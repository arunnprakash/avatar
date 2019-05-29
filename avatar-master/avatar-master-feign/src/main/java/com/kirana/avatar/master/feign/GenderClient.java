/**
 * 
 */
package com.kirana.avatar.master.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.kirana.avatar.common.feign.config.FeignClientContract;
import com.kirana.avatar.master.resource.GenderResource;
/**
 * @author __Telmila__
 *
 */
@FeignClient(name="gender-service", configuration={FeignClientContract.class})
public interface GenderClient extends GenderResource {

}
