package org.joker.oscp.pay.alipay.relevant.service;

import org.joker.oscp.pay.alipay.relevant.model.builder.AlipayTradePayRequestBuilder;
import org.joker.oscp.pay.alipay.relevant.model.builder.AlipayTradePrecreateRequestBuilder;
import org.joker.oscp.pay.alipay.relevant.model.builder.AlipayTradeQueryRequestBuilder;
import org.joker.oscp.pay.alipay.relevant.model.builder.AlipayTradeRefundRequestBuilder;
import org.joker.oscp.pay.alipay.relevant.model.result.AlipayF2FPayResult;
import org.joker.oscp.pay.alipay.relevant.model.result.AlipayF2FPrecreateResult;
import org.joker.oscp.pay.alipay.relevant.model.result.AlipayF2FQueryResult;
import org.joker.oscp.pay.alipay.relevant.model.result.AlipayF2FRefundResult;

/**
 * Created by liuyangkly on 15/7/29.
 */
public interface AlipayTradeService {

    // 当面付2.0流程支付
    public AlipayF2FPayResult tradePay(AlipayTradePayRequestBuilder builder);

    // 当面付2.0消费查询
    public AlipayF2FQueryResult queryTradeResult(AlipayTradeQueryRequestBuilder builder);

    // 当面付2.0消费退款
    public AlipayF2FRefundResult tradeRefund(AlipayTradeRefundRequestBuilder builder);

    // 当面付2.0预下单(生成二维码)
    public AlipayF2FPrecreateResult tradePrecreate(AlipayTradePrecreateRequestBuilder builder);
}
