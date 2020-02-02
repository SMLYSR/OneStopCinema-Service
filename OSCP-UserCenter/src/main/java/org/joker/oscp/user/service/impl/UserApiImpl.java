package org.joker.oscp.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.joker.oscp.system.api.user.UserApi;
import org.joker.oscp.system.api.user.vo.UserInfoModel;
import org.joker.oscp.system.api.user.vo.UserModel;
import org.joker.oscp.user.dao.UserMapper;
import org.joker.oscp.user.entity.User;
import org.joker.oscp.user.entity.UserJoin;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户API的实现
 * @author JOKER
 */
@Component
public class UserApiImpl implements UserApi {

    private UserMapper userMapper;

    @Autowired
    public UserApiImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public int login(String username, String password) {
        return 0;
    }

    @Override
    public boolean register(UserModel userModel) {
        return false;
    }

    @Override
    public boolean checkUsername(String username) {
        return false;
    }

    @Override
    public UserInfoModel getUserInfo(int uuid) {
        return null;
    }

    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        return null;
    }

    @Override
    public UserModel selectByUsername(String username) {

        UserJoin userJoin = userMapper.selectByUsername(username);

        UserModel userModel = new UserModel();

        userModel.setUsername(userJoin.getUserName());
        userModel.setPassword(userJoin.getUserPwd());
        userModel.setUserState(userJoin.getUserState());
        userModel.setUUID(userJoin.getUuid());

        return userModel;
    }
}
