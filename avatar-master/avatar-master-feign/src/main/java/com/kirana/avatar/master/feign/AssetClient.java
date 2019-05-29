/**
 * 
 */
package com.kirana.avatar.master.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.kirana.avatar.common.feign.config.FeignClientContract;
import com.kirana.avatar.master.resource.AssetResource;
/**
 * @author __Telmila__
 *
 */
@FeignClient(name="asset-service", configuration={FeignClientContract.class})
public interface AssetClient extends AssetResource {

}
