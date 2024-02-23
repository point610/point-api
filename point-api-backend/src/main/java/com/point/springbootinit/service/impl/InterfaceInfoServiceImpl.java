package com.point.springbootinit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.point.springbootinit.model.entity.InterfaceInfo;
import com.point.springbootinit.service.InterfaceInfoService;
import com.point.springbootinit.mapper.InterfaceInfoMapper;
import org.springframework.stereotype.Service;

/**
* 接口信息
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{

}




