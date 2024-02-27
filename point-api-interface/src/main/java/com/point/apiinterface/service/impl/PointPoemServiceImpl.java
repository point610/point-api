package com.point.apiinterface.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.point.apiinterface.model.entity.PointPoem;
import com.point.apiinterface.service.PointPoemService;
import com.point.apiinterface.mapper.PointPoemMapper;
import org.springframework.stereotype.Service;

/**
* @author 29684
* @description 针对表【point_poem(诗词)】的数据库操作Service实现
* @createDate 2024-02-27 17:00:11
*/
@Service
public class PointPoemServiceImpl extends ServiceImpl<PointPoemMapper, PointPoem>
    implements PointPoemService{

}




