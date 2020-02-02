package org.joker.oscp.system.api.user;

import org.joker.oscp.system.api.user.vo.UserInfoModel;
import org.joker.oscp.system.api.user.vo.UserModel;
import org.springframework.stereotype.Component;

/**
 * 远程调用的用户中心接口
 * @author JOKER
 */
@Component
public interface UserApi {

    /**
     * 用户登录
     * @param username
     * @param password
     * @return 是否登录成功
     */
    int login(String username, String password);

    /**
     * 用户注册
     * @param userModel
     * @return 是否注册成功
     */
    boolean register(UserModel userModel);

    /**
     * 检查用户名是否存在
     * @param username
     * @return 时候存在
     */
    boolean checkUsername(String username);

    /**
     * 获取用户信息
     * @param uuid
     * @return 返回 {@link UserInfoModel}
     */
    UserInfoModel getUserInfo(int uuid);

    /**
     * 更新用户信息
     * @param userInfoModel
     * @return 返回更新后的 {@link UserInfoModel}
     */
    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);

    /**
     * 鉴权登录时的用户查询
     * @param username
     * @return
     */
    UserModel selectByUsername(String username);
}
