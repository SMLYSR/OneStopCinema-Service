package org.joker.oscp.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户实体类
 * @author JOKER
 */
@Data
@TableName("uc_user")
public class User {

    /**
     * 主键编号
     */

    @TableId
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

}
