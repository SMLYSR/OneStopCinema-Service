package org.joker.oscp.gateway.security.service;

import org.joker.oscp.system.api.user.vo.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 远程调用用户中心的接口
 * @author JOKER
 */
@FeignClient("OSCP-UserCenter")
public interface UserCenterFeigned {

    /**
     * <p>远程调用</p>
     *  根据用户名查找User
     * @param username
     * @return {@link UserModel}
     */
    @RequestMapping(method = RequestMethod.POST, value = "/selectByUsername")
    UserModel selectByUsername(String username);

}
