package com.point.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.point.springbootinit.model.entity.UserInterfaceInfo;
import com.point.springbootinit.service.UserInterfaceInfoService;
import com.point.springbootinit.mapper.UserInterfaceInfoMapper;
import org.springframework.stereotype.Service;

/**
* 用户调用接口关系
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService{

}




