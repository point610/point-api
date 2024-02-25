package com.point.apiinterface.controller;

import com.point.apiinterface.common.BaseResponse;
import com.point.apiinterface.common.ResultUtils;
import com.point.apiinterface.mapper.PointBoringTalkMapper;
import com.point.apiinterface.model.vo.PointBoringTalkVO;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 名称 API
 */
@RestController
@RequestMapping("/boringtalk")
public class BoringTalkController {

    @Resource
    private PointBoringTalkMapper pointBoringTalkMapper;

    @PostMapping("/random")
    public BaseResponse<PointBoringTalkVO> getRandomBoringTalk() {
        PointBoringTalkVO pointBoringTalkVO = new PointBoringTalkVO();
        BeanUtils.copyProperties(pointBoringTalkMapper.selectRandomBoringTalk(), pointBoringTalkVO);
        return ResultUtils.success(pointBoringTalkVO);
    }
}
