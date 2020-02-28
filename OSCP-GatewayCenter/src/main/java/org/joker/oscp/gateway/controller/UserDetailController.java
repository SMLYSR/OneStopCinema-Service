package org.joker.oscp.gateway.controller;


import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.common.CommonResult;
import org.joker.oscp.gateway.security.service.UserCenterFeigned;
import org.joker.oscp.gateway.util.JwtTokenUtil;
import org.joker.oscp.system.api.user.vo.UserInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户接口层
 * @author JOKER
 */
@Slf4j
@RestController
public class UserDetailController {

    private UserCenterFeigned userCenterFeigned;
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    public UserDetailController(UserCenterFeigned userCenterFeigned, JwtTokenUtil jwtTokenUtil) {
        this.userCenterFeigned = userCenterFeigned;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping(value = "/checkUsername")
    public CommonResult checkUsername(@RequestParam(value = "username") String username) {
        if (username != null && username.trim().length() > 0) {
            boolean isExists = userCenterFeigned.checkUsername(username);
            if (isExists) {
                return CommonResult.usernameExists();
            } else {
                return CommonResult.usernameNotExists();
            }
        } else {
            return CommonResult.serviceFailed("用户名不能为空");
        }
    }



}
