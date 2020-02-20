package org.joker.oscp.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.common.CommonResult;
import org.joker.oscp.common.util.CurrentUser;
import org.joker.oscp.gateway.security.model.SecurityUserDetails;
import org.joker.oscp.gateway.security.service.UserCenterFeigned;
import org.joker.oscp.gateway.util.JwtTokenUtil;
import org.joker.oscp.gateway.vo.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

/**
 * 鉴权控制器
 * @author JOKER
 */
@Slf4j
@RestController
public class AuthController {

    private UserCenterFeigned userCenterFeigned;

    private AuthenticationManager authenticationManager;

    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    public AuthController(UserCenterFeigned userCenterFeigned, AuthenticationManager authenticationManager,
                          JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userCenterFeigned = userCenterFeigned;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userCenterFeigned = userCenterFeigned;
    }

    @RequestMapping(value = "${jwt.auth-path}", method = RequestMethod.POST)
    public CommonResult createAuthenticationToken(@RequestBody AuthRequest authRequest) {

        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword());

        try {
            Authentication  authentication = authenticationManager.authenticate(authenticationToken);
            log.debug("已完成鉴权！权限为：{}", authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String userAuthorizationToken = jwtTokenUtil.generateToken(authRequest.getUsername());
            // TODO: 2020/2/20 远程调用用户中心，查询用户ID
            Long userId = userCenterFeigned.getUserIdByUsername(authRequest.getUsername());
            CurrentUser.saveUserId(userId);
            return CommonResult.success(userAuthorizationToken,"鉴权成功！");
        } catch (org.springframework.security.core.AuthenticationException e) {
            log.info("鉴权失败！！！");
            return CommonResult.forbidden();
        }
    }
}
