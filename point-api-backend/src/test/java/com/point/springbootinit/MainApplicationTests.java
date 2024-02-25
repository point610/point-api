package com.point.springbootinit;

import java.util.Date;


import javax.annotation.Resource;

import cn.hutool.core.date.DateTime;
import com.point.apisdk.client.PointApiClient;
import com.point.springbootinit.model.entity.InterfaceInfo;
import com.point.springbootinit.model.entity.UserInterfaceInfo;
import com.point.springbootinit.service.InterfaceInfoService;
import com.point.springbootinit.service.UserInterfaceInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 主类测试
 */
@SpringBootTest
class MainApplicationTests {

    @Resource
    private InterfaceInfoService interfaceInfoService;
    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;
    @Resource
    private PointApiClient pointApiClient;

    @Test
    void contextLoads() {
        for (int i = 0; i < 100; i++) {
            UserInterfaceInfo userInterfaceInfo = new UserInterfaceInfo();
            userInterfaceInfo.setUserId(1761214571856367618L);
            userInterfaceInfo.setInterfaceInfoId(7L);
            userInterfaceInfo.setTotalNum(10);
            userInterfaceInfo.setLeftNum(10);
            userInterfaceInfo.setStatus(1);
            userInterfaceInfo.setCreateTime(new DateTime());
            userInterfaceInfo.setUpdateTime(new DateTime());
            userInterfaceInfo.setIsDelete(0);

            userInterfaceInfoService.save(userInterfaceInfo);

        }
    }

    @Test
    void interfacetest() {
        System.out.println(pointApiClient.getRandomBoringTalk());
    }

}







