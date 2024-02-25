package com.point.apisdk.client;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.point.apisdk.common.BaseResponse;
import com.point.apisdk.exception.BusinessException;
import com.point.apisdk.model.entity.PointBoringTalk;
import com.point.apisdk.utils.SignUtils;


import java.util.HashMap;

import static com.point.apisdk.utils.CheckUtils.CheckResponse;


/**
 * 调用第三方接口的客户端
 */
public class PointApiClient {

    private static final String PATH = "http://localhost:8123";

    private String accessKey;

    private String secretKey;

    public PointApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getRandomBoringTalk() {
        // 将用于请求的对象装换为json
        String json = JSONUtil.toJsonStr("");

        HttpResponse httpResponse = HttpRequest.post(PATH + "/api/boringtalk/random")
                .addHeaders(SignUtils.GetHeaderMap(json, accessKey, secretKey))
                .body(json)
                .execute();
        BaseResponse baseResponse = CheckResponse(httpResponse);

        PointBoringTalk pointBoringTalk = JSONUtil.toBean(JSONUtil.toJsonStr(baseResponse.getData()), PointBoringTalk.class);

        return pointBoringTalk.getContent();
    }
}
