package com.point.apiinterface.mapper;

import com.point.apiinterface.model.entity.PointBoringTalk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 无聊的话
 */
public interface PointBoringTalkMapper extends BaseMapper<PointBoringTalk> {
    // 在 Mapper 接口中
     PointBoringTalk selectRandomBoringTalk();

}




