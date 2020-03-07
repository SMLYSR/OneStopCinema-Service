package org.joker.oscp.community.test_dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.community.dao.UserDetailMapper;
import org.joker.oscp.community.dao.UserMapper;
import org.joker.oscp.community.entity.User;
import org.joker.oscp.community.entity.UserDetail;
import org.joker.oscp.community.entity.UserJoin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestUserMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailMapper userDetailMapper;

    @Test
    public void contextLoads() {
        log.info("UserMapper: {}", this.userMapper);
    }

    @Test
    public void test() {
        System.out.printf("hhhhhhh");
    }

    // 测试用户注册、登录
    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUserName("JOKER");
        user.setUserPwd("123456789");
        user.setUserState(1);
        int insertedUserState = userMapper.insert(user);
        log.info("用户插入状态: {}", insertedUserState);
        // 必须保证用户名唯一
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", user.getUserName());
        User nextUser = userMapper.selectOne(queryWrapper);
        log.info("当前用户Id为：{}", nextUser.getUuid());
        // 插入到UserDetail中
        UserDetail userDetail = new UserDetail();
        userDetail.setUuid(nextUser.getUuid());
        int insertedUserDetailState = userDetailMapper.insert(userDetail);
        log.info("用户详情插入状态: {}", insertedUserDetailState);
    }

    @Test
    public void testSelectUser() {
        String username = "JOKER";
        String userpwd = "123456789";
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", username);
        User user = userMapper.selectOne(queryWrapper);
        log.info("用户名：{}", user.getUserName());
        log.info("用户密码：{}", user.getUserPwd());

        if (user.getUserPwd().equals(userpwd)) {
            log.info("登录成功!");
        } else {
            log.info("密码错误!");
        }
    }

    // 录入用户详细信息
    @Test
    public void testUpdateUserDetail() {
        Long userId = 1222901482101440514L;
        // 字符串转LocalData
        String str = "1998-01-11";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(str, fmt);

        UserDetail userDetail = new UserDetail();
        userDetail.setUuid(userId);
        userDetail.setNickName("joker");
        userDetail.setEmail("1305887161@qq.com");
        userDetail.setBirthday(date);
        int updateState = userDetailMapper.updateById(userDetail);
        log.info("用户详情更新状态: {}", updateState);
    }

    // 用户连接查询
    @Test
    public void testJoinUser() {
        String username = "JOKER";
        UserJoin userJoin = userMapper.selectByUsername(username);
        log.info("result: {}", userJoin);
    }
}
