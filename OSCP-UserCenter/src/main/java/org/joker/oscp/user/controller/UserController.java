package org.joker.oscp.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.common.CommonResult;
import org.joker.oscp.common.ResultCode;
import org.joker.oscp.system.api.user.UserApi;
import org.joker.oscp.system.api.user.vo.UserInfoModel;
import org.joker.oscp.system.api.user.vo.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户中心对外暴露的REST
 * @author JOKER
 */
@Slf4j
@RestController()
@RequestMapping()
public class UserController {

    private UserApi userApi;

    @Autowired
    public UserController(UserApi userApi) {
        this.userApi = userApi;
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public UserInfoModel getUserInfo(@RequestParam(value = "uuid") Long uuid) {
        if (uuid != null) {
            return userApi.getUserInfo(uuid);
        }
        return null;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public boolean register(@RequestBody UserModel userModel) {
        if (userModel != null){
            return userApi.register(userModel);
        }
        return false;
    }

    @RequestMapping(value = "/checkUsername", method = RequestMethod.POST)
    public boolean checkUsername(@RequestParam(value = "username") String username) {
        if (username != null){
            boolean isUsername = userApi.checkUsername(username);
            if (isUsername) {
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public UserInfoModel updateUserInfo(@RequestBody UserInfoModel userInfoModel) {
        if (userInfoModel != null) {
            return userApi.updateUserInfo(userInfoModel);
        }
        return null;
    }

    @RequestMapping(value = "/selectByUsername", method = RequestMethod.POST)
    public CommonResult selectByUsername(@RequestParam(value = "username") String username) {
        UserModel user = userApi.selectByUsername(username);
        log.info("{}",user);
        if (user != null) {
            return CommonResult.success(user,"调用成功！");
        }
        return CommonResult.serviceFailed("用户中心查询失败");
    }

}
