package org.joker.oscp.system.api.order;

import com.baomidou.mybatisplus.plugins.Page;
import org.joker.oscp.system.api.order.vo.OrderVO;

/**
 * 订单中心Api接口
 * @author JOKER
 */
public interface OrderCenterApi {

    /**
     * 验证售出的票是否为真
     * @param fieldId 影厅Id
     * @param seats 选择的座位号
     * @return 真票即返回true
     */
    boolean isTrueSeats(Long fieldId, String seats);

    /**
     * 已经销售的座位里，有没有这些座位
     * @param fieldId 影厅Id
     * @param seats 选择的座位号
     * @return 没有即返回true
     */
    boolean isNotSoldSeats(Long fieldId, String seats);

    /**
     * 创建订单信息
     * @param fieldId 影厅Id
     * @param soldSeats 该订单售出的座位
     * @param seatsName 座位名称
     * @param userId 下单用户
     * @return {@link OrderVO}
     */
    OrderVO saveOrderInfo(Long fieldId, String soldSeats, String seatsName, Long userId);

    /**
     * 使用当前登陆人获取已经购买的订单
     * @param userId 用户Id
     * @param page 分页项
     * @return 分页项
     */
    Page<OrderVO> getOrderByUserId(Long userId, Page<OrderVO> page);

    /**
     * 根据FieldId 获取所有已经销售的座位编号
     * @param fieldId 影厅Id
     * @return 已经销售的座位编号
     */
    String getSoldSeatsByFieldId(Long fieldId);
}
