package org.joker.oscp.gateway.test_controller;

import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.gateway.security.service.UserCenterFeigned;
import org.joker.oscp.gateway.security.service.impl.UserCenterFeignedImpl;
import org.joker.oscp.gateway.util.JwtTokenUtil;
import org.joker.oscp.system.api.user.vo.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.AbstractList;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestController {

    @Autowired
    private UserCenterFeigned userCenterFeigned;


    @Test
    public void testFeign() {
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();

        Class c = jwtTokenUtil.getClass();
        log.info("class {}", c);
    }
}
