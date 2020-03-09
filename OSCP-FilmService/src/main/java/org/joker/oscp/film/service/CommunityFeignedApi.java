package org.joker.oscp.film.service;

import org.joker.oscp.system.api.community.vo.LatestCommunityInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p> 远程调用 </p>
 * 社区服务
 *
 * @author JOKER
 */
@Service
@FeignClient(value = "oscp-community-service", path = "/community")
public interface CommunityFeignedApi {

    /**
     * <p> 远程调用 </p>
     * 获取最新社区活动和最新影评
     *
     * @return {@link LatestCommunityInfo}
     */
    @GetMapping(value = "/getCommunityInfoByIndex")
    LatestCommunityInfo getCommunityInfoByIndex();

}
