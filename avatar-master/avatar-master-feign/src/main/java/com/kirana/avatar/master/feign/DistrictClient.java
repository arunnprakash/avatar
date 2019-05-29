/**
 * 
 */
package com.kirana.avatar.master.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.kirana.avatar.common.feign.config.FeignClientContract;
import com.kirana.avatar.master.resource.DistrictResource;
/**
 * @author __Telmila__
 *
 */
@FeignClient(name="district-service", configuration={FeignClientContract.class})
public interface DistrictClient extends DistrictResource {

}
