package com.point.apiinterface.controller;

import com.point.apiinterface.common.BaseResponse;
import com.point.apiinterface.common.ResultUtils;
import com.point.apiinterface.mapper.PointSoulfulSanctuaryMapper;
import com.point.apiinterface.model.entity.PointPoem;
import com.point.apiinterface.model.entity.PointSoulfulSanctuary;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 名称 API
 */
@RestController
@RequestMapping("/soulfulsanctuary")
public class SoulfulSanctuaryController {

    @Resource
    private PointSoulfulSanctuaryMapper pointSoulfulSanctuaryMapper;

    @PostMapping("/random")
    public BaseResponse<PointSoulfulSanctuary> getRandomPeom() {
        PointSoulfulSanctuary pointSoulfulSanctuary = new PointSoulfulSanctuary();
        BeanUtils.copyProperties(pointSoulfulSanctuaryMapper.selectRandomSoulfulSanctuary(), pointSoulfulSanctuary);
        return ResultUtils.success(pointSoulfulSanctuary);
    }
}
