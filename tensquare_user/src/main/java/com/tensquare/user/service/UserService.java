package com.tensquare.user.service;

import com.tensquare.user.dao.UserMapper;
import com.tensquare.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @PackageName: com.tensquare.user.service
 * @ClassName: UserService
 * @Author: Nicolas.Chang
 * @Date: 2020/2/25 22:32
 * @Description:
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User findById(String userId){
        User user = userMapper.selectById(userId);
        return user;
    }

}
