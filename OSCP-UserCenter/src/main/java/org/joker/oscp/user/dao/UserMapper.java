package org.joker.oscp.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.joker.oscp.user.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户数据 Mapper
 * @author JOKER
 */
@Repository
public interface UserMapper extends BaseMapper<User> {}
