package org.joker.oscp.system.api.payment;

import org.joker.oscp.system.api.payment.vo.AliPayInfoVO;
import org.joker.oscp.system.api.payment.vo.AliPayResultVO;

/**
 * 订单中心服务接口
 *
 * @author JOKER
 */
public interface PaymentCenterApi {

    /**
     * 获取订单二维码
     *
     * @param orderId 交易订单
     * @return {@link AliPayInfoVO}
     */
    AliPayInfoVO getQRCode(String orderId);

    /**
     * 获取当前订单状态
     *
     * @param orderId 交易订单
     * @return {@link AliPayResultVO}
     */
    AliPayResultVO getOrderStatus(String orderId);

}
