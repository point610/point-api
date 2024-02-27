package com.point.apicommon.service;


/**
 * 内部接口信息服务
 */
public interface InnerRedisService {

    /**
     * 将nonce保存到redis中
     * redis中不存在，且保存成功，返回true
     * redis中存在，or保存不成功，返回false
     */
    boolean putRedisNone(long interfaceInfoId, long userId, String nonce);
}
