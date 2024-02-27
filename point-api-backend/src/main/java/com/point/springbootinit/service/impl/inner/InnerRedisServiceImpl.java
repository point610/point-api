package com.point.springbootinit.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.point.apicommon.service.InnerRedisService;
import com.point.springbootinit.common.ErrorCode;
import com.point.springbootinit.common.ResultUtils;
import com.point.springbootinit.exception.ThrowUtils;
import com.point.springbootinit.manager.CacheManager;
import io.swagger.models.auth.In;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

import static com.point.springbootinit.constant.RedisConstant.NONE_PREFIX;

/**
 * 内部接口服务实现类
 */
@DubboService
public class InnerRedisServiceImpl implements InnerRedisService {
    @Resource
    private CacheManager cacheManager;

    /**
     * 获取分页缓存key
     *
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    public static String getRedisCacheKey(long interfaceInfoId, long userId, String nonce) {
        String key = NONE_PREFIX + userId + ":" + interfaceInfoId + ":" + nonce;
        return key;
    }

    @Override
    public boolean putRedisNone(long interfaceInfoId, long userId, String nonce) {
        // 从缓存读取
        String cacheKey = getRedisCacheKey(interfaceInfoId, userId, nonce);
        String cacheValue = cacheManager.get(cacheKey);
        if (cacheValue != null) {
            System.out.println("cacheValue != null------->" + cacheValue);
            System.out.println("cacheValue != null------->" + nonce);
            return false;
        }

        System.out.println("cacheValue == null------->" + cacheValue);
        System.out.println("cacheValue == null------->" + nonce);

        // 写入缓存
        cacheManager.put(cacheKey, "");
        return true;
    }
}
