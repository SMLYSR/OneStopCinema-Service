package org.joker.oscp.user.entity;

import lombok.Data;

import java.sql.Date;

/**
 * 用户实体类
 * @author JOKER
 */
@Data
public class UserModel {

    /**
     * 主键编号
     */
    private Long uuid;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPwd;

    /**
     * 账户状态
     */
    private int userState;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户性别
     */
    private UserSexEnum  userSex;

    /**
     * 用户生日
     */
    private Date birthday;

    /**
     * 用户生日
     */
    private String email;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户地址
     */
    private String address;

    /**
     * 用户头像URL
     */
    private String handUrl;

    /**
     * 用户简介
     */
    private  String biography;

}
