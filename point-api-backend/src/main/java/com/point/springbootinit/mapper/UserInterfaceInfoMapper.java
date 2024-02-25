package com.point.springbootinit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.point.apicommon.model.entity.UserInterfaceInfo;

import java.util.List;

/**
 * 用户调用接口关系
 */
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);

}




