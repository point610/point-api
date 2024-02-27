package com.point.apiinterface.controller;

import com.point.apiinterface.common.BaseResponse;
import com.point.apiinterface.common.ResultUtils;
import com.point.apiinterface.mapper.PointPoemMapper;
import com.point.apiinterface.model.entity.PointPoem;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 名称 API
 */
@RestController
@RequestMapping("/poem")
public class PoemController {

    @Resource
    private PointPoemMapper pointPoemMapper;

    @PostMapping("/random")
    public BaseResponse<PointPoem> getRandomPeom() {
        PointPoem pointPoem = new PointPoem();
        BeanUtils.copyProperties(pointPoemMapper.selectRandomPoem(), pointPoem);
        return ResultUtils.success(pointPoem);
    }
}
