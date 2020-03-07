package org.joker.oscp.community.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * BC编码处理工具
 * @author JOKER
 */
public class BcUtil {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * bc编码
     * @param password
     * @return 编码后的密码
     */
    public  String bcPassword(String password) {
        return passwordEncoder.encode(password.trim());
    }

}
