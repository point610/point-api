package com.point.apiinterface.mapper;

import com.point.apiinterface.model.entity.PointSoulfulSanctuary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author 29684
 * @description 针对表【point_soulful_sanctuary(心灵鸡汤)】的数据库操作Mapper
 * @createDate 2024-02-27 17:00:11
 * @Entity com.point.apiinterface.model.entity.PointSoulfulSanctuary
 */
public interface PointSoulfulSanctuaryMapper extends BaseMapper<PointSoulfulSanctuary> {
    PointSoulfulSanctuary selectRandomSoulfulSanctuary();
}




