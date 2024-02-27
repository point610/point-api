package com.point.apiinterface.mapper;

import com.point.apiinterface.model.entity.PointBoringTalk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.point.apiinterface.model.entity.PointPoem;

/**
 * @author 29684
 * @description 针对表【point_poem(诗词)】的数据库操作Mapper
 * @createDate 2024-02-27 17:00:11
 * @Entity com.point.apiinterface.model.entity.PointPoem
 */
public interface PointPoemMapper extends BaseMapper<PointPoem> {
    PointPoem selectRandomPoem();

}




