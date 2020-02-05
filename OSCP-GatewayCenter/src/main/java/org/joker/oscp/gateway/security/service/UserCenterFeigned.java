package org.joker.oscp.gateway.security.service;

import org.joker.oscp.common.CommonResult;
import org.joker.oscp.gateway.security.service.fallback.UserCenterFallBackFactory;
import org.joker.oscp.system.api.user.vo.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用用户中心的接口
 * @author JOKER
 */
@Service
@FeignClient(value = "oscp-user-center", path = "/user", fallbackFactory = UserCenterFallBackFactory.class)
public interface UserCenterFeigned {

    /**
     * <p>远程调用</p>
     *  根据用户名查找User
     * @param username
     * @return {@link UserModel}
     */
    @RequestMapping(method = RequestMethod.POST, value = "/selectByUsername")
    CommonResult selectByUsername(@RequestParam(value = "username") String username);

    /**
     * <p>远程调用</p>
     *  检查用户是否存在
     * @param username
     * @return 存在即返回true
     */
    @GetMapping(value = "/checkUsername")
    boolean checkUsername(@RequestParam String username);

}
