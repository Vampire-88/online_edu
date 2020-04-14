package com.online.school.edu.mapper;

import com.online.school.edu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.online.school.edu.entity.response.CourseInfo;
import com.online.school.edu.entity.response.TeacherAllInfoDto;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-01-10
 */
public interface CourseMapper extends BaseMapper<EduCourse> {

    CourseInfo getCourseInfo(String courseId);

    TeacherAllInfoDto getTeacherAllInfo(String id);
}
