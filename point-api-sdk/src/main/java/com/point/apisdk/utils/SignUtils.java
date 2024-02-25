package com.point.apisdk.utils;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.util.HashMap;
import java.util.Map;

/**
 * 签名工具
 */
public class SignUtils {
    /**
     * 生成签名
     *
     * @param body
     * @param secretKey
     * @return
     */
    public static String GenSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = body + "." + secretKey;
        return md5.digestHex(content);
    }

    /**
     * 获取请求头信息
     *
     * @param body
     * @param accessKey
     * @param secretKey
     * @return
     */
    public static Map<String, String> GetHeaderMap(String body, String accessKey, String secretKey) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", GenSign(body, secretKey));
        return hashMap;
    }
}
