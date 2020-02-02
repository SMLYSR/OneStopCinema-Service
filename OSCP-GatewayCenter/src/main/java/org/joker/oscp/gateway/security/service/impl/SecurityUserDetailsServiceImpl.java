package org.joker.oscp.gateway.security.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.gateway.security.model.SecurityUserDetails;
import org.joker.oscp.gateway.security.service.UserCenterFeigned;
import org.joker.oscp.system.api.user.vo.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Security重载loadUserByUsername
 * @author JOKER
 */
@Primary
@Service
@Slf4j
public class SecurityUserDetailsServiceImpl implements UserDetailsService {

    // TODO: 2020/2/1
    // 这里需要涉及到Gateway远程调用UserCenter,查询用户信息

    private UserCenterFeigned userCenterFeigned;

    @Autowired
    public SecurityUserDetailsServiceImpl(UserCenterFeigned userCenterFeigned) {
        this.userCenterFeigned = userCenterFeigned;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 这一步将取出所有的用户信息供Security验证（密码，状态等）

        UserModel user = userCenterFeigned.selectByUsername(username);

        if (user != null) {
            return new SecurityUserDetails(user.getUsername(), user.getPassword(), user.getUserState());
        }

        log.info("用户不存在！！！！");
        return null;
    }
}
