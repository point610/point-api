package com.point.springbootinit.manager;

import com.point.springbootinit.constant.RedisConstant;
import io.swagger.models.auth.In;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 多级缓存
 */
@Component
public class CacheManager {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        String encodedKey = new String(key.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        redisTemplate.opsForValue().set(encodedKey, value, 5, TimeUnit.MINUTES);
    }

    /**
     * 读缓存
     *
     * @param key
     * @return
     */
    public String get(String key) {
        // 从 Redis 获取
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 清空缓存
     */
    public void deleteAll() {
        Set<String> keys = redisTemplate.keys(RedisConstant.NONE_PREFIX + "*");
        if (keys != null) {
            redisTemplate.delete(keys);
        }
    }
}
