package com.point.apisdk.client;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.point.apisdk.common.BaseResponse;
import com.point.apisdk.model.entity.PointBoringTalk;
import com.point.apisdk.model.entity.PointPoem;
import com.point.apisdk.model.entity.PointSoulfulSanctuary;
import com.point.apisdk.model.entity.PointUser;
import com.point.apisdk.utils.SignUtils;


import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static com.point.apisdk.utils.CheckUtils.CheckResponse;


/**
 * 调用第三方接口的客户端
 */
public class PointApiClient {

    private static final String GATEWAY_PATH = "http://localhost:8090";

    private String accessKey;

    private String secretKey;

    public PointApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getRandomBoringTalk() {
        // 将用于请求的对象装换为json
        String json = JSONUtil.toJsonStr("");

        HttpResponse httpResponse = HttpRequest.post(GATEWAY_PATH + "/api/boringtalk/random")
                .addHeaders(SignUtils.GetHeaderMap(json, accessKey, secretKey))
                .body(json)
                .execute();
        BaseResponse baseResponse = CheckResponse(httpResponse);

        PointBoringTalk pointBoringTalk = JSONUtil.toBean(JSONUtil.toJsonStr(baseResponse.getData()), PointBoringTalk.class);

        return pointBoringTalk.getValue();
    }

    public String getRandomSoulfulSanctuary() {
        // 将用于请求的对象装换为json
        String json = JSONUtil.toJsonStr("");

        HttpResponse httpResponse = HttpRequest.post(GATEWAY_PATH + "/api/soulfulsanctuary/random")
                .addHeaders(SignUtils.GetHeaderMap(json, accessKey, secretKey))
                .body(json)
                .execute();
        BaseResponse baseResponse = CheckResponse(httpResponse);

        PointSoulfulSanctuary pointSoulfulSanctuary = JSONUtil.toBean(JSONUtil.toJsonStr(baseResponse.getData()), PointSoulfulSanctuary.class);

        return pointSoulfulSanctuary.getValue();
    }

    public String getRandomPoem() {
        // 将用于请求的对象装换为json
        String json = JSONUtil.toJsonStr("");

        HttpResponse httpResponse = HttpRequest.post(GATEWAY_PATH + "/api/poem/random")
                .addHeaders(SignUtils.GetHeaderMap(json, accessKey, secretKey))
                .body(json)
                .execute();
        BaseResponse baseResponse = CheckResponse(httpResponse);

        PointPoem pointPoem = JSONUtil.toBean(JSONUtil.toJsonStr(baseResponse.getData()), PointPoem.class);

        return pointPoem.getValue();
    }

    public String getUserName(String userRequestParams) {
        // 将用于请求的对象装换为json
        String json = userRequestParams;

        HttpResponse httpResponse = HttpRequest.post(GATEWAY_PATH + "/api/test/user")
                .addHeaders(SignUtils.GetHeaderMap(json, accessKey, secretKey))
                .body(json)
                .execute();
        BaseResponse baseResponse = CheckResponse(httpResponse);

        PointUser pointUser = JSONUtil.toBean(JSONUtil.toJsonStr(baseResponse.getData()), PointUser.class);

        return pointUser.getName();
    }
}
