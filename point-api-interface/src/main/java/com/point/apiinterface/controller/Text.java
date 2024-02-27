package com.point.apiinterface.controller;

import com.point.apiinterface.common.BaseResponse;
import com.point.apiinterface.common.ResultUtils;
import com.point.apiinterface.model.entity.PointUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 名称 API
 */
@RestController
@RequestMapping("/test")
public class Text {

    @PostMapping("/user")
    public BaseResponse<PointUser> getUserName(@RequestBody PointUser pointUser) {
        return ResultUtils.success(pointUser);
    }

}
