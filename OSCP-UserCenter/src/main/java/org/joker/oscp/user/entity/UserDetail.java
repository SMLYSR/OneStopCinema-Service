package org.joker.oscp.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

/**
 * 用户详情实体
 * @author JOKER
 */
@Data
@TableName("uc_user_detail")
public class UserDetail {
    /**
     * 主键编号
     */
    @TableId
    private Long uuid;
    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户性别
     */
    private int userSex;

    /**
     * 用户生日
     */
    private LocalDate birthday;

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
    @TableField("head_url")
    private String handUrl;

    /**
     * 用户简介
     */
    private  String biography;
}
