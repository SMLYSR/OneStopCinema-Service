package org.joker.oscp.gateway.security.service.fallback;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.common.CommonResult;
import org.joker.oscp.gateway.security.service.UserCenterFeigned;
import org.joker.oscp.gateway.security.service.impl.UserCenterFeignedImpl;
import org.springframework.stereotype.Component;

/**
 * fallback回调工厂类
 * @author JOKER
 */
@Slf4j
@Component
public class UserCenterFallBackFactory implements FallbackFactory<UserCenterFeigned> {
    @Override
    public UserCenterFeigned create(Throwable cause) {
        log.info("用户中心服务调用失败：{}", cause.getMessage() == null ? "null" : cause.getMessage());
        cause.printStackTrace();
        return new UserCenterFeignedImpl() {

            @Override
            public CommonResult selectByUsername(String username) {
                return CommonResult.serviceFailed("用户中心服务调用失败！！！");
            }

            @Override
            public boolean checkUsername(String username) {
                return false;
            }

            @Override
            public Long getUserIdByUsername(String username) {
                return null;
            }
        };
    }
}
