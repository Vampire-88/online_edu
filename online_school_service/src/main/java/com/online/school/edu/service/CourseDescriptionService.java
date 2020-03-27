package com.online.school.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.online.school.edu.entity.EduCourseDescription;

public interface CourseDescriptionService extends IService<EduCourseDescription> {
    void deleteDescriptionByCourseId(String id);
}
