package org.joker.oscp.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.system.api.user.UserApi;
import org.joker.oscp.system.api.user.vo.UserModel;
import org.joker.oscp.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户中心对外暴露的REST
 * @author JOKER
 */
@Slf4j
@RestController()
@RequestMapping("user")
public class UserController {

    private UserApi userApi;

    @Autowired
    public UserController(UserApi userApi) {
        this.userApi = userApi;
    }

    @RequestMapping(value = "/selectByUsername", method = RequestMethod.POST)
    public UserModel selectByUsername(@RequestParam String username) {
        UserModel user = userApi.selectByUsername(username);
        log.info("{}",user);
        return user;
    }



}
