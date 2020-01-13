package com.online.school.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.online.school.edu.entity.EduCourse;
import com.online.school.edu.entity.request.CourseInfoRequest;

public interface EduCourseService extends IService<EduCourse> {

    /**
    * 功能描述 : 添加课程信息
    *
    * @param  courseInfoRequest : linked{CourseInfoRequest}
    * @return
    * @author 王威
    * @created 2020-01-13 10:50
    */
    String insertCourseInfo(CourseInfoRequest courseInfoRequest);

    /**
    * 功能描述 : 修改课程信息
    *
    * @param  courseInfoRequest : linked{CourseInfoRequest}
    * @return
    * @author 王威
    * @created 2020-01-13 14:53
    */
    void updateCourseInfo(CourseInfoRequest courseInfoRequest);

    /**
    * 功能描述 : 查询课程信息
    *
    * @param
    * @return
    * @author 王威
    * @created 2020-01-13 14:53
    */
    CourseInfoRequest getCourseInfoBy(String id);
}
