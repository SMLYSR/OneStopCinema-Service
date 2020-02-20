package org.joker.oscp.order.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
import org.joker.oscp.order.entity.OrderT;
import org.joker.oscp.system.api.order.vo.OrderVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 订单信息表 Mapper 接口
 * </p>
 *
 * @author JOKER
 */
@Repository
public interface OrderTMapper extends BaseMapper<OrderT> {

    String getSeatsByFieldId(@Param("fieldId") Long fieldId);

    OrderVO getOrderInfoById(@Param("orderId") String orderId);

    List<OrderVO> getOrdersByUserId(@Param("userId") Long userId, Page<OrderVO> page);

    String getSoldSeatsByFieldId(@Param("fieldId") Long fieldId);

}
