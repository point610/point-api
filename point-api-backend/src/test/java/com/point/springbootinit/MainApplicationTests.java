package com.point.springbootinit;

import java.util.Date;

import cn.hutool.core.date.DateTime;
import com.point.springbootinit.config.WxOpenConfig;

import javax.annotation.Resource;

import com.point.springbootinit.model.entity.InterfaceInfo;
import com.point.springbootinit.service.InterfaceInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 主类测试
 */
@SpringBootTest
class MainApplicationTests {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Test
    void contextLoads() {
        for (int i = 0; i < 1000; i++) {
            InterfaceInfo interfaceInfo = new InterfaceInfo();
            interfaceInfo.setId(null);
            interfaceInfo.setPicture("https://i.ibb.co/sF2ZCkL/generator-picture-1752189699880771586-88-Bxv-VFI-Snipaste-2024-02-21-11-17-56-png7640397440270497328.png");
            interfaceInfo.setName(i + "这是一个接口");
            interfaceInfo.setDescription(i + "这是一个接口的描述");
            interfaceInfo.setUrl("usl");
            interfaceInfo.setRequestParams("{application / json}");
            interfaceInfo.setRequestHeader("{application / json}");
            interfaceInfo.setResponseHeader("{application / json}");
            interfaceInfo.setStatus(0);
            interfaceInfo.setMethod("post");
            interfaceInfo.setUserId(1761214571856367618L);
            interfaceInfo.setCreateTime(new DateTime());
            interfaceInfo.setUpdateTime(new DateTime());
            interfaceInfo.setIsDelete(0);
            interfaceInfoService.save(interfaceInfo);
        }
    }

}
