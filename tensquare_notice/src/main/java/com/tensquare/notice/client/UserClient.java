package com.tensquare.notice.client;

import com.tensquare.entity.Result;
import com.tensquare.notice.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @PackageName: com.tensquare.notice.client
 * @ClassName: UserClient
 * @Author: Nicolas.Chang
 * @Date: 2020/2/25 23:03
 * @Description:
 */


@FeignClient(name = "tensquare-user")
public interface UserClient {

    @RequestMapping(value = "user/{userId}",method = RequestMethod.POST)
    public Result<User> findById(@PathVariable("userId") String userId);

}
