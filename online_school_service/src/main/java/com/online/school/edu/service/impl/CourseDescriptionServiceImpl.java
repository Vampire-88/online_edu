package com.online.school.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.school.edu.entity.EduCourseDescription;
import com.online.school.edu.entity.Video;
import com.online.school.edu.mapper.CourseDescriptionMapper;
import com.online.school.edu.service.CourseDescriptionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-13
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, EduCourseDescription> implements CourseDescriptionService {

    @Override
    public void deleteDescriptionByCourseId(String id) {
        QueryWrapper<EduCourseDescription> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        baseMapper.delete(queryWrapper);
    }
}
