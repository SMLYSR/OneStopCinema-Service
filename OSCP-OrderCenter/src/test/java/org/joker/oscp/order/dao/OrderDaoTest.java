package org.joker.oscp.order.dao;


import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.order.entity.OrderT;
import org.joker.oscp.order.util.UUIDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDaoTest {

    @Autowired
    private OrderTMapper orderTMapper;

    @Test
    public void testOrderTest() {
        OrderT orderT = new OrderT();
        orderT.setUuid(UUIDUtil.getUuid());
        orderT.setCinemaId(1L);
        orderT.setFieldId(1L);
        orderT.setFilmId(1227069975103971329L);
        orderT.setSeatsIds("1,2,3,4");
        orderT.setSeatsName("第一排1座,第一排2座,第一排3座,第一排4座");
        orderT.setFilmPrice(BigDecimal.valueOf(63.2));
        orderT.setOrderPrice(BigDecimal.valueOf(252.8));
        orderT.setOrderUser(1222901482101440514L);

        Integer insert = orderTMapper.insert(orderT);
        log.info("受影响行数：{}",insert);
    }

}
