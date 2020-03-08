package org.joker.oscp.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.joker.oscp.user.entity.User;
import org.joker.oscp.user.entity.UserJoin;
import org.springframework.stereotype.Repository;

/**
 * 用户数据 Mapper
 * @author JOKER
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 连接查询用户所有的信息
     * @param username
     * @return 组合后的用户
     */
    UserJoin selectByUsername(@Param("username") String username);


}
