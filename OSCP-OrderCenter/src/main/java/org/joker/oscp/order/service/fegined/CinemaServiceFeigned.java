package org.joker.oscp.order.service.fegined;

import org.joker.oscp.common.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.joker.oscp.system.api.cinema.vo.FilmInfoVO;
import org.joker.oscp.system.api.cinema.vo.OrderQueryVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用影院服务
 * @author JOKER
 */
@Service
@FeignClient(value = "oscp-cinema-service", path = "/cinema")
public interface CinemaServiceFeigned {

    /**
     * <p>远程调用</p>
     * 根据院厅Id获取影片信息
     * @param fieldId 院厅Id
     * @return {@link FilmInfoVO}
     */
    @RequestMapping(value = "/getFilmInfoByFieldId")
    CommonResult getFilmInfoByFieldId(@RequestParam(value = "fieldId")Long fieldId);

    /**
     * <p>远程调用</p>
     * 根据院厅Id获取影片信息
     * @param fieldId 院厅Id
     * @return {@link OrderQueryVO}
     */
    @RequestMapping(value = "/getOrderNeeds")
    CommonResult getOrderNeeds(@RequestParam(value = "fieldId")Long fieldId);
}
