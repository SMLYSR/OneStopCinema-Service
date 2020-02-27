package org.joker.oscp.pay.service.impl;

import com.alipay.api.response.AlipayTradePrecreateResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joker.oscp.pay.alipay.relevant.config.Configs;
import org.joker.oscp.pay.alipay.relevant.model.ExtendParams;
import org.joker.oscp.pay.alipay.relevant.model.GoodsDetail;
import org.joker.oscp.pay.alipay.relevant.model.builder.AlipayTradePrecreateRequestBuilder;
import org.joker.oscp.pay.alipay.relevant.model.builder.AlipayTradeQueryRequestBuilder;
import org.joker.oscp.pay.alipay.relevant.model.result.AlipayF2FPrecreateResult;
import org.joker.oscp.pay.alipay.relevant.model.result.AlipayF2FQueryResult;
import org.joker.oscp.pay.alipay.relevant.service.AlipayMonitorService;
import org.joker.oscp.pay.alipay.relevant.service.AlipayTradeService;
import org.joker.oscp.pay.alipay.relevant.service.impl.AlipayMonitorServiceImpl;
import org.joker.oscp.pay.alipay.relevant.service.impl.AlipayTradeServiceImpl;
import org.joker.oscp.pay.alipay.relevant.service.impl.AlipayTradeWithHBServiceImpl;
import org.joker.oscp.pay.alipay.relevant.utils.ZxingUtils;
import org.joker.oscp.pay.service.feigned.OrderFeignedApi;
import org.joker.oscp.pay.util.OSSReader;
import org.joker.oscp.pay.util.UtilResult;
import org.joker.oscp.system.api.order.vo.OrderVO;
import org.joker.oscp.system.api.payment.PaymentCenterApi;
import org.joker.oscp.system.api.payment.vo.AliPayInfoVO;
import org.joker.oscp.system.api.payment.vo.AliPayResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * 支付中心接口实现
 *
 * @author JOKER
 */
@Component
public class PaymentCenterServiceImpl implements PaymentCenterApi {
    protected Log log = LogFactory.getLog(getClass());

    /**
     * 支付宝当面付2.0服务
     */
    private static AlipayTradeService tradeService;
    private static AlipayTradeService tradeWithHBService;
    private static AlipayMonitorService monitorService;

    static {
        Configs.init("zfbinfo.properties");
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
        tradeWithHBService = new AlipayTradeWithHBServiceImpl.ClientBuilder().build();
        monitorService = new AlipayMonitorServiceImpl.ClientBuilder()
                .setGatewayUrl("http://mcloudmonitor.com/gateway.do").setCharset("GBK")
                .setFormat("json").build();
    }

    private OrderFeignedApi orderFeignedApi;
    private OSSReader ossReader;

    @Autowired
    public PaymentCenterServiceImpl(OrderFeignedApi orderFeignedApi, OSSReader ossReader) {
        this.orderFeignedApi = orderFeignedApi;
        this.ossReader = ossReader;
    }

    @Override
    public AliPayInfoVO getQRCode(String orderId) {
        String filePath = tradePrecreate(orderId);
        if (filePath == null || filePath.trim().length() == 0) {
            return null;
        } else {
            AliPayInfoVO aliPayInfoVO = new AliPayInfoVO();
            aliPayInfoVO.setOrderId(orderId);
            aliPayInfoVO.setQRCodeAddress(filePath);
            return aliPayInfoVO;
        }
    }

    @Override
    public AliPayResultVO getOrderStatus(String orderId) {

        boolean isSuccess = tradeQuery(orderId);
        if (isSuccess) {
            AliPayResultVO aliPayResultVO = new AliPayResultVO();
            aliPayResultVO.setOrderId(orderId);
            aliPayResultVO.setOrderStatus(1);
            aliPayResultVO.setOrderMsg("支付成功");
            return aliPayResultVO;
        }
        return null;
    }

    /**
     * 集成支付宝支付
     *
     * @param orderId
     * @return
     */
    public String tradePrecreate(String orderId) {
        String filePath = "";
        // 获取订单信息
        OrderVO orderVO = orderFeignedApi.getOrderInfoById(orderId);

        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        String outTradeNo = orderId;

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        String subject = "OSCP在线票务";

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = orderVO.getOrderPrice();

        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = "购买电影票共花费" + orderVO.getOrderPrice();

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "JOKER";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "JOKER";

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");

        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";

        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<>();

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
                .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
                .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
                .setTimeoutExpress(timeoutExpress)
                //                .setNotifyUrl("http://www.test-notify-url.com")//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setGoodsDetailList(goodsDetailList);

        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();

                // 需要修改为运行机器上的路径
                filePath = String.format("C:/Users/lenovo/Desktop/QRcode/qr-%s.png",
                        response.getOutTradeNo());
                String fileName = String.format("qr-%s.png", response.getOutTradeNo());
                log.info("filePath:" + filePath);
                /**
                 * 生成二维码至本地路径
                 * 上传至oss
                 * 删除本地二维码
                 * 若上述两项有一个不成功,则判定生成失败
                 */
                File qrCodeImge = ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
                UtilResult utilResult = ossReader.updateFileInOSS(ossReader.tranfromByFile(qrCodeImge));

                if (!utilResult.isFlag()) {
                    filePath = "";
                    log.error("二维码上传失败");
                } else {
                    filePath = utilResult.getPath();
                }
                break;

            case FAILED:
                log.error("支付宝预下单失败!!!");
                break;

            case UNKNOWN:
                log.error("系统异常，预下单状态未知!!!");
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                break;
        }
        return filePath;
    }

    /**
     * 测试当面付2.0查询订单
     * @param orderId 订单编号
     * @return
     */
    public boolean tradeQuery(String orderId) {
        boolean flag = false;
        // (必填) 商户订单号，通过此商户订单号查询当面付的交易状态
        String outTradeNo = orderId;

        // 创建查询请求builder，设置请求参数
        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
                .setOutTradeNo(outTradeNo);

        AlipayF2FQueryResult result = tradeService.queryTradeResult(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("查询返回该订单支付成功: )");

                // 当订单支付成功状态时，修改订单状态为1
                flag = orderFeignedApi.paySuccess(orderId);

                break;

            case FAILED:
                log.error("查询返回该订单支付失败或被关闭!!!");
                break;

            case UNKNOWN:
                log.error("系统异常，订单支付状态未知!!!");
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                break;
        }
        return flag;
    }
}
