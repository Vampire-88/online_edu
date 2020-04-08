package com.online.school.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.school.edu.entity.EduCourse;
import com.online.school.edu.entity.request.CourseInfoRequest;
import com.online.school.edu.entity.request.CourseRequest;
import com.online.school.edu.entity.response.CourseInfo;
import com.online.school.edu.entity.response.TeacherAllInfoDto;

import java.util.Map;

public interface CourseService extends IService<EduCourse> {

    /**
     * 功能描述 : 添加课程信息
     *
     * @param courseInfoRequest : linked{CourseInfoRequest}
     * @return
     * @author 王威
     * @created 2020-01-13 10:50
     */
    String insertCourseInfo(CourseInfoRequest courseInfoRequest);

    /**
     * 功能描述 : 修改课程信息
     *
     * @param courseInfoRequest : linked{CourseInfoRequest}
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

    /**
     * 功能描述 : 分页条件查找
     *
     * @param eduCoursePage : 分页参数
     * @param courseRequest : linked{CourseRequest}
     * @return
     * @author 王威
     * @created 2020-01-14 18:00
     */
    boolean getCourseConditionPageList(Page<EduCourse> eduCoursePage, CourseRequest courseRequest);

    /**
     * 功能描述 : 根据课程id删除课程，描述，小结。
     *
     * @param
     * @return
     * @author 王威
     * @created 2020-01-14 16:50
     */
    void removeCourseById(String id);

    /**
     * 功能描述 :
     *
     * @param
     * @return
     * @author 王威
     * @created 2020-01-14 16:50
     */
    CourseInfo getAllCourseInfo(String courseId);

    void deleteCourseById(String id);

    Map<String, Object> listCoursePage(Page<EduCourse> pageCourse);

    TeacherAllInfoDto getTeacherAllInfo(String id);
}
