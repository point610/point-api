package com.point.springbootinit.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.point.springbootinit.model.dto.interfaceinfo.InterfaceInfoQueryRequest;
import com.point.springbootinit.model.dto.user.UserQueryRequest;
import com.point.springbootinit.model.entity.InterfaceInfo;
import com.point.springbootinit.model.entity.User;

/**
 * 接口信息服务
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

    /**
     * 获取查询条件
     */
    QueryWrapper<InterfaceInfo> getQueryWrapper(InterfaceInfoQueryRequest interfaceInfoQueryRequest);
}
