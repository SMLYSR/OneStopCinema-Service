package org.joker.oscp.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.common.CommonResult;
import org.joker.oscp.system.api.user.UserApi;
import org.joker.oscp.system.api.user.vo.UserInfoModel;
import org.joker.oscp.system.api.user.vo.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户中心对外暴露的REST
 *
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

    @RequestMapping(value = "/getUserInfo1", method = RequestMethod.GET)
    public CommonResult getUserInfoByMsg(@RequestParam(value = "uuid") String uuid) {
        if (uuid != null) {
            UserInfoModel userInfoModel = userApi.getUserInfo(uuid);
            if (userInfoModel != null) {
                return CommonResult.success(userInfoModel, "操作成功!");
            } else {
                return CommonResult.serviceFailed("业务失败");
            }
        }
        return CommonResult.failed("系统异常，请查看日志！");
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public UserInfoModel getUserInfo(@RequestParam(value = "username") String username) {
        if (username != null) {
            UserInfoModel userInfoModel = userApi.getUserInfo(username);
            if (userInfoModel != null) {
                return userInfoModel;
            } else {
                return null;
            }
        }
        return null;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult register(@RequestBody UserModel userModel) {
        if (userModel != null) {
            boolean isRegister = userApi.register(userModel);
            if (!isRegister) {
                return CommonResult.serviceFailed("注册失败");
            } else {
                return CommonResult.success(true);
            }
        }
        log.info("入参错误，为空!!!");
        return CommonResult.failed("系统异常，请查看日志！");
    }

    @RequestMapping(value = "/checkUsername", method = RequestMethod.GET)
    public boolean checkUsername(@RequestParam(value = "username") String username) {
        if (username != null) {
            boolean isUsername = userApi.checkUsername(username);
            return isUsername;
        }
        log.info("入参错误，为空!!!");
        return false;
    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public CommonResult updateUserInfo(@RequestBody UserInfoModel userInfoModel) {
        if (userInfoModel != null) {
            UserInfoModel newUserInfoModel = userApi.updateUserInfo(userInfoModel);
            if (newUserInfoModel != null) {
                return CommonResult.success(newUserInfoModel);
            } else {
                return CommonResult.serviceFailed("用户信息更新失败！");
            }
        }
        log.info("入参错误，为空!!!");
        return CommonResult.failed("系统异常，请查看日志！");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public CommonResult logout() {
        return CommonResult.success("退出成功");
    }

    @RequestMapping(value = "/selectByUsername", method = RequestMethod.POST)
    public CommonResult selectByUsername(@RequestParam(value = "username") String username) {
        UserModel user = userApi.selectByUsername(username);
        log.info("{}", user);
        if (user != null) {
            return CommonResult.success(user, "调用成功！");
        }
        return CommonResult.serviceFailed("用户中心查询失败");
    }

    @RequestMapping(value = "/getUserIdByUsername", method = RequestMethod.POST)
    public Long getUserIdByUsername(@RequestParam(value = "username") String username) {
        return userApi.getUserId(username);
    }


}
