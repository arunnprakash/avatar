/**
 * 
 */
package com.kirana.avatar.master.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.kirana.avatar.common.feign.config.FeignClientContract;
import com.kirana.avatar.master.resource.TalukResource;
/**
 * @author __Telmila__
 *
 */
@FeignClient(name="taluk-service", configuration={FeignClientContract.class})
public interface TalukClient extends TalukResource {

}
