package org.joker.oscp.pay.service.feigned;

import org.joker.oscp.system.api.order.vo.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>远程调用</p>
 * 订单中心服务
 *
 * @author JOKER
 */
@Service
@FeignClient(value = "oscp-order-center", path = "/order")
public interface OrderFeignedApi {

    /**
     * <p>【远程调用】</p>
     * 根据订单编号获取订单信息
     *
     * @param orderId 订单编号
     * @return 没有即返回true
     */
    @PostMapping(value = "/getOrderInfoById")
    OrderVO getOrderInfoById(@RequestParam(value = "orderId") String orderId);

    /**
     * <p>【远程调用】</p>
     * 验证订单成功
     * @param orderId 订单编号
     * @return
     */
    @PostMapping(value = "/paySuccess")
    boolean paySuccess(@RequestParam(value = "orderId") String orderId);

}
