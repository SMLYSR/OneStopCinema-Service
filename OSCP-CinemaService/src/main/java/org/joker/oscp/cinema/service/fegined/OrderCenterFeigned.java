package org.joker.oscp.cinema.service.fegined;


import org.joker.oscp.common.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用订单中心
 * @author JOKER
 */
@Service
@FeignClient(value = "oscp-order-center", path = "/order")
public interface OrderCenterFeigned {

    /**
     * <p>远程调用</p>
     * 获取所有已经销售的座位编号
     * @param fieldId 影厅Id
     * @return 已经销售的座位编号
     */
    @RequestMapping(value = "/getSoldSeatsByFieldId", method = RequestMethod.POST)
    CommonResult getSoldSeatsByFieldId(@RequestParam(value = "fieldId")Long fieldId);

}
