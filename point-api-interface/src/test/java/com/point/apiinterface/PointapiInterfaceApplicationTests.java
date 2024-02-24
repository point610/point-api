package com.point.apiinterface;

import com.point.apisdk.client.PointApiClient;
import com.point.apisdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 测试类
 */
@SpringBootTest
class PointapiInterfaceApplicationTests {

    @Resource
    private PointApiClient pointApiClient;

    @Test
    void contextLoads() {
        String result = pointApiClient.getNameByGet("point");
        User user = new User();
        user.setUsername("point");
        String usernameByPost = pointApiClient.getUsernameByPost(user);
        System.out.println(result);
        System.out.println(usernameByPost);
    }

}
