package com.point.springbootinit.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.point.springbootinit.model.dto.interfaceinfo.InterfaceInfoQueryRequest;
import com.point.springbootinit.model.dto.userinterfaceinfo.UserInterfaceInfoQueryRequest;
import com.point.springbootinit.model.entity.InterfaceInfo;
import com.point.springbootinit.model.entity.UserInterfaceInfo;

/**
 * 用户接口信息服务
 */
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {


    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 调用接口统计
     *
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);

    /**
     * 获取查询条件
     */
    QueryWrapper<UserInterfaceInfo> getQueryWrapper(UserInterfaceInfoQueryRequest userInterfaceInfoQueryRequest);
}
