package org.joker.oscp.gateway.vo;

import lombok.Data;

/**
 * 用于接收来自前端的用户数据
 * @author JOKER
 */
@Data
public class AuthRequest {

    private String username;

    private String password;
}
