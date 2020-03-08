package org.joker.oscp.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.system.api.user.UserApi;
import org.joker.oscp.system.api.user.vo.UserInfoModel;
import org.joker.oscp.system.api.user.vo.UserModel;
import org.joker.oscp.user.dao.UserDetailMapper;
import org.joker.oscp.user.dao.UserMapper;
import org.joker.oscp.user.entity.User;
import org.joker.oscp.user.entity.UserDetail;
import org.joker.oscp.user.entity.UserJoin;
import org.joker.oscp.user.util.BcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户API的实现
 * @author JOKER
 */
@Slf4j
@Component
public class UserApiImpl implements UserApi {

    private UserMapper userMapper;
    private UserDetailMapper userDetailMapper;

    @Autowired
    public UserApiImpl(UserMapper userMapper, UserDetailMapper userDetailMapper) {
        this.userMapper = userMapper;
        this.userDetailMapper = userDetailMapper;
    }


    @Override
    public int login(String username, String password) {
        return 0;
    }

    @Override
    public boolean register(UserModel userModel) {
        User user = new User();
        user.setUserName(userModel.getUsername());
        user.setUserState(1);
        BcUtil bcUtil = new BcUtil();
        String bcPassword = bcUtil.bcPassword(userModel.getPassword());
        user.setUserPwd(bcPassword);
        Integer insert = userMapper.insert(user);

        // 必须保证用户名唯一
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", user.getUserName());
        User nextUser = userMapper.selectOne(queryWrapper);
        log.info("当前用户Id为：{}", nextUser.getUuid());
        // 插入到UserDetail中
        UserDetail userDetail = new UserDetail();
        userDetail.setUuid(nextUser.getUuid());
        int insertedUserDetailState = userDetailMapper.insert(userDetail);
        log.info("用户详情插入状态: {}", insertedUserDetailState);

        if (insert > 0 && insertedUserDetailState > 0) {
            log.info("插入成功: {}", user);
            return true;
        } else {
            log.info("插入失败!!!");
            return false;
        }
    }

    @Override
    public boolean checkUsername(String username) {
        if (username != null) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("user_name",username);
            User user = userMapper.selectOne(queryWrapper);
            if (user != null) {
                log.info("该用户存在: {}",user);
                return true;
            } else {
                log.info("该用户不存在!!! ");
            }
        }
        return false;
    }

    @Override
    public UserInfoModel getUserInfo(Long uuid) {
        UserDetail userDetail = userDetailMapper.selectById(uuid);
        if (userDetail != null ) {
            UserInfoModel userInfoModel = new UserInfoModel();
            userInfoModel.setUuid(String.valueOf(userDetail.getUuid()));
            userInfoModel.setNickname(userDetail.getNickName());
            userInfoModel.setAddress(userDetail.getAddress());
            userInfoModel.setBirthday(userDetail.getBirthday());
            userInfoModel.setEmail(userDetail.getEmail());
            userInfoModel.setHeadAddress(userDetail.getHandUrl());
            userInfoModel.setPhone(userDetail.getPhone());
            userInfoModel.setSex(userDetail.getUserSex());
            userInfoModel.setBiography(userDetail.getBiography());
            return userInfoModel;
        }
        log.info("参数错误，该用户不存在，uuid：%s",uuid);
        return null;
    }

    @Override
    public UserInfoModel getUserInfo(String username) {
        UserJoin userJoin = userMapper.selectByUsername(username);
        if (userJoin != null ) {
            UserInfoModel userInfoModel = new UserInfoModel();
            userInfoModel.setUuid(String.valueOf(userJoin.getUuid()));
            userInfoModel.setNickname(userJoin.getNickName());
            userInfoModel.setAddress(userJoin.getAddress());
            userInfoModel.setBirthday(userJoin.getBirthday());
            userInfoModel.setEmail(userJoin.getEmail());
            userInfoModel.setHeadAddress(userJoin.getHandUrl());
            userInfoModel.setPhone(userJoin.getPhone());
            userInfoModel.setSex(userJoin.getUserSex());
            userInfoModel.setBiography(userJoin.getBiography());
            return userInfoModel;
        }
        log.info("参数错误，该用户不存在，username：%s",username);
        return null;
    }

    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        if (userInfoModel != null) {
            UserDetail userDetail = new UserDetail();
            userDetail.setUuid(Long.valueOf(userInfoModel.getUuid()));
            userDetail.setNickName(userInfoModel.getNickname());
            userDetail.setUserSex(userInfoModel.getSex());
            userDetail.setEmail(userInfoModel.getEmail());
            userDetail.setAddress(userInfoModel.getAddress());
            userDetail.setBirthday(userInfoModel.getBirthday());
            userDetail.setBiography(userInfoModel.getBiography());
            userDetail.setHandUrl(userInfoModel.getHeadAddress());
            userDetail.setPhone(userInfoModel.getPhone());

            Integer integer = userDetailMapper.updateById(userDetail);
            if (integer > 0) {
                log.info("更新成功");
                UserInfoModel userInfo = getUserInfo(userInfoModel.getUuid());
                return userInfo;
            } else {
                log.info("uuid参数错误，用户信息关联错误！！！ %s",userInfoModel.getUuid());
            }
        }
        return null;
    }

    @Override
    public UserModel selectByUsername(String username) {

        UserJoin userJoin = userMapper.selectByUsername(username);

        if (userJoin != null) {
            UserModel userModel = new UserModel();
            userModel.setUUID(userJoin.getUuid());
            userModel.setUsername(userJoin.getUserName());
            userModel.setPassword(userJoin.getUserPwd());
            userModel.setUserState(userJoin.getUserState());
            return userModel;
        }
        return null;
    }

    @Override
    public Long getUserId(String username) {
        QueryWrapper entityWriter = new QueryWrapper();
        entityWriter.eq("user_name",username);
        User user = userMapper.selectOne(entityWriter);
        return user.getUuid();
    }
}
