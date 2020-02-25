package com.tensquare.user.controller;

import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PackageName: com.tensquare.user.controller
 * @ClassName: UserController
 * @Author: Nicolas.Chang
 * @Date: 2020/2/25 22:36
 * @Description:
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userId}",method = RequestMethod.POST)
    public Result<User> findById(@PathVariable("userId") String userId){
        User user = userService.findById(userId);
        return new Result(true, StatusCode.OK,"查询成功",user);
    }

}
