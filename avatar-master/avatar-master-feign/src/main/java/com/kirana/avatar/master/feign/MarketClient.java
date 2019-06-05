/**
 * 
 */
package com.kirana.avatar.master.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.kirana.avatar.common.feign.config.FeignClientContract;
import com.kirana.avatar.master.resource.MarketResource;
/**
 * @author __Telmila__
 *
 */
@FeignClient(name="master-service", configuration={FeignClientContract.class})
public interface MarketClient extends MarketResource {

}
