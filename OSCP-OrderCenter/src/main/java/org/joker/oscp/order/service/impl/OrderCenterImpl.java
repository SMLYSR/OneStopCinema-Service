package org.joker.oscp.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.common.CommonResult;
import org.joker.oscp.common.util.ResultDataConvertValue;
import org.joker.oscp.order.dao.OrderTMapper;
import org.joker.oscp.order.entity.OrderT;
import org.joker.oscp.order.service.fegined.CinemaServiceFeigned;
import org.joker.oscp.order.util.OSSReader;
import org.joker.oscp.order.util.UUIDUtil;
import org.joker.oscp.system.api.cinema.vo.FilmInfoVO;
import org.joker.oscp.system.api.cinema.vo.OrderQueryVO;
import org.joker.oscp.system.api.order.OrderCenterApi;
import org.joker.oscp.system.api.order.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


/**
 * 订单中心Aip实现类
 *
 * @author JOKER
 */
@Slf4j
@Component
public class OrderCenterImpl implements OrderCenterApi {

    private OrderTMapper orderTMapper;
    private OSSReader ossReader;
    private CinemaServiceFeigned cinemaServiceFeigned;

    @Autowired
    public OrderCenterImpl(OrderTMapper orderTMapper, OSSReader ossReader, CinemaServiceFeigned cinemaServiceFeigned) {
        this.orderTMapper = orderTMapper;
        this.ossReader = ossReader;
        this.cinemaServiceFeigned = cinemaServiceFeigned;
    }

    @Override
    public boolean isTrueSeats(Long fieldId, String seats) {
        // 根据fieldId查找座位文件
        String seatPath = orderTMapper.getSeatsByFieldId(fieldId);

        ResponseEntity<String> seatJsonByOSS = ossReader.getSeatJsonByOSS(seatPath);
        JsonObject jsonObject = new JsonParser().parse(seatJsonByOSS.getBody()).getAsJsonObject();

        String ids = jsonObject.get("ids").toString();

        String[] seatArrs = seats.split(",");
        String[] idArrs = ids.split(",");
        int isTrue = 0;
        for (String id : idArrs) {
            for (String seat : seatArrs) {
                if (seat.equalsIgnoreCase(id)) {
                    isTrue++;
                }
            }
        }
        // 如果匹配上的数量与已售座位数一致，则表示全都匹配上了
        if (seatArrs.length == isTrue) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isNotSoldSeats(Long fieldId, String seats) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("field_id", fieldId);

        List<OrderT> list = orderTMapper.selectList(entityWrapper);
        String[] seatArrs = seats.split(",");
        for (OrderT orderT : list) {
            String[] ids = orderT.getSeatsIds().split(",");
            for (String id : ids) {
                for (String seat : seatArrs) {
                    if (id.equalsIgnoreCase(seat)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public OrderVO saveOrderInfo(Long fieldId, String soldSeats, String seatsName, Long userId) {
        Long filmId = -1L, cinemaId = -1L;
        BigDecimal filmPrice = null;
        final int SUCCESS_FLAG = 200;
        // 订单编号这次手动维护
        String uuid = UUIDUtil.getUuid();

        // 获取影片信息
        CommonResult filmInfoCR = cinemaServiceFeigned.getFilmInfoByFieldId(fieldId);
        if (filmInfoCR.getCode() == SUCCESS_FLAG) {
            ResultDataConvertValue<FilmInfoVO> resultDataConvertValue = new ResultDataConvertValue();
            FilmInfoVO filmInfoVO = resultDataConvertValue.
                    obResultDataConvert(filmInfoCR, new TypeReference<FilmInfoVO>() {
                    });
            if (filmInfoVO != null) {
                filmId = Long.valueOf(filmInfoVO.getFilmId());
            }
            log.error("数据错误，无法查询出filmInfo，{}", fieldId);
        } else {
            // TODO: 2020/2/20 暂时输出错误日志，后期需要加入服务熔断和降级
            log.error("影院服务响应失败！！！");
        }

        // 获取影院信息
        CommonResult orderQueryCR = cinemaServiceFeigned.getOrderNeeds(fieldId);
        if (orderQueryCR.getCode() == SUCCESS_FLAG) {
            ResultDataConvertValue<OrderQueryVO> resultDataConvertValue = new ResultDataConvertValue();
            OrderQueryVO queryVO = resultDataConvertValue.
                    obResultDataConvert(orderQueryCR, new TypeReference<OrderQueryVO>() {
                    });
            if (queryVO != null) {
                cinemaId = queryVO.getCinemaId();
                filmPrice = queryVO.getFilmPrice();
            }
            log.error("数据错误，无法查询出queryVO，{}", fieldId);
        } else {
            // TODO: 2020/2/20 暂时输出错误日志，后期需要加入服务熔断和降级
            log.error("影院服务响应失败！！！");
        }

        // 计算订单总金额
        int solds = soldSeats.split(",").length;
        BigDecimal totalPrice = getTotalPrice(solds, filmPrice);

        // 组织订单对象
        OrderT orderT = new OrderT();
        orderT.setSeatsName(seatsName);
        orderT.setSeatsIds(soldSeats);
        orderT.setOrderUser(userId);
        orderT.setOrderPrice(totalPrice);
        orderT.setFilmPrice(filmPrice);
        orderT.setFilmId(filmId);
        orderT.setFieldId(fieldId);
        orderT.setCinemaId(cinemaId);
        orderT.setUuid(uuid);

        Integer insert = orderTMapper.insert(orderT);
        if (insert > 0) {
            OrderVO orderVO = orderTMapper.getOrderInfoById(uuid);
            if (orderVO == null || orderVO.getOrderId() == null) {
                log.info("订单信息查询失败,订单编号为{}", uuid);
                return null;
            } else {
                return orderVO;
            }
        } else {
            log.error("订单插入失败！！！");
            return null;
        }
    }

    @Override
    public Page<OrderVO> getOrderByUserId(Long userId, Page<OrderVO> page) {
        Page<OrderVO> result = new Page<>();
        if (userId == null) {
            log.error("订单查询业务失败，用户编号未传入");
            return null;
        } else {
            List<OrderVO> ordersByUserId = orderTMapper.getOrdersByUserId(userId, page);
            if (ordersByUserId == null && ordersByUserId.size() == 0) {
                result.setTotal(0);
                result.setRecords(new ArrayList<>());
                return result;
            } else {
                // 获取订单总数
                EntityWrapper<OrderT> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("order_user", userId);
                Integer counts = orderTMapper.selectCount(entityWrapper);
                // 将结果放入Page
                result.setTotal(counts);
                result.setRecords(ordersByUserId);
                return result;
            }
        }
    }

    @Override
    public String getSoldSeatsByFieldId(Long fieldId) {
        if (fieldId == null) {
            log.error("查询已售座位错误，未传入任何场次编号");
            return "";
        } else {
            String soldSeatsByFieldId = orderTMapper.getSoldSeatsByFieldId(fieldId);
            return soldSeatsByFieldId;
        }
    }

    @Override
    public OrderVO getOrderInfoById(String orderId) {
        OrderVO orderInfoById = orderTMapper.getOrderInfoById(orderId);
        return orderInfoById;
    }

    @Override
    public boolean paySuccess(String orderId) {
        OrderT orderT = new OrderT();
        orderT.setUuid(orderId);
        orderT.setOrderStatus(1);

        Integer integer = orderTMapper.updateById(orderT);
        if (integer >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean payFail(String orderId) {
        OrderT orderT = new OrderT();
        orderT.setUuid(orderId);
        orderT.setOrderStatus(2);

        Integer integer = orderTMapper.updateById(orderT);
        if (integer >= 1) {
            return true;
        } else {
            return false;
        }
    }

    private static BigDecimal getTotalPrice(int solds, BigDecimal filmPrice) {
        BigDecimal soldsDeci = new BigDecimal(solds);

        BigDecimal result = soldsDeci.multiply(filmPrice);

        // 四舍五入，取小数点后两位
        BigDecimal bigDecimal = result.setScale(2, RoundingMode.HALF_UP);

        return bigDecimal;
    }
}
