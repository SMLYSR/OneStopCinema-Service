package org.joker.oscp.gateway.security.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.common.CommonResult;
import org.joker.oscp.common.util.ResultDataConvertValue;
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

    private final int SUCCESS_FLAG = 200;

    // TODO: 2020/2/1
    // 这里需要涉及到Gateway远程调用UserCenter,查询用户信息

    @Autowired
    private UserCenterFeigned userCenterFeigned;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 这一步将取出所有的用户信息供Security验证（密码，状态等）

        try {
            CommonResult commonResult = userCenterFeigned.selectByUsername(username);
            if (commonResult.getCode() == SUCCESS_FLAG) {
                // 返回的统一对结果中的对象为LinkedHashMap类型，需要进行类型转换
                ResultDataConvertValue<UserModel> resultDataConvertValue = new ResultDataConvertValue();
                UserModel user = resultDataConvertValue.obResultDataConvert(commonResult, new TypeReference<UserModel>() {});
                if (user != null) {

                    return new SecurityUserDetails(user.getUsername(), user.getPassword(), user.getUserState());
                }
                log.info("用户不存在！！！！");
                return null;
            }
        } catch (Exception e) {
            log.info("服务调用失败");
        }
        return null;
    }
}
