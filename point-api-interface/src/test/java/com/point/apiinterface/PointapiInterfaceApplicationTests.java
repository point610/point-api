package com.point.apiinterface;

import com.point.apiinterface.service.PointBoringTalkService;
import com.point.apisdk.client.PointApiClient;
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

    @Resource
    private PointBoringTalkService pointBoringTalkService;


    @Test void  addTest(){
    }


}
