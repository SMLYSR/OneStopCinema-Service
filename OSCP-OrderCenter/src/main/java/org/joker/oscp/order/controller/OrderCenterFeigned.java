package org.joker.oscp.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.common.CommonResult;
import org.joker.oscp.system.api.order.OrderCenterApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>供远程调用使用</p>
 * @author JOKER
 */
@Slf4j
@RestController
public class OrderCenterFeigned {

    private OrderCenterApi orderCenterApi;

    @Autowired
    public OrderCenterFeigned(OrderCenterApi orderCenterApi) {
        this.orderCenterApi = orderCenterApi;
    }

    @RequestMapping(value = "/getSoldSeatsByFieldId", method = RequestMethod.POST)
    public CommonResult getSoldSeatsByFieldId(@RequestParam(value = "fieldId")Long fieldId) {
        String soldSeatsByFieldId = orderCenterApi.getSoldSeatsByFieldId(fieldId);
        return CommonResult.success(soldSeatsByFieldId);
    }
}
