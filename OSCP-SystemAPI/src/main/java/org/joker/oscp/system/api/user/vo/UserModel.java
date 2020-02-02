package org.joker.oscp.system.api.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 拱接口调用的VO
 * @author JOKER
 */
@Data
public class UserModel implements Serializable {

    private Long UUID;

    private String username;

    private String password;

    private int userState;
}
