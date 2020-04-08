package com.online.school.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.school.edu.entity.EduCourse;
import com.online.school.edu.entity.EduCourseDescription;
import com.online.school.edu.entity.request.CourseInfoRequest;
import com.online.school.edu.entity.request.CourseRequest;
import com.online.school.edu.entity.response.CourseInfo;
import com.online.school.edu.entity.response.TeacherAllInfoDto;
import com.online.school.edu.handler.EduException;
import com.online.school.edu.mapper.CourseMapper;
import com.online.school.edu.service.ChapterService;
import com.online.school.edu.service.CourseDescriptionService;
import com.online.school.edu.service.CourseService;
import com.online.school.edu.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-10
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, EduCourse> implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @Resource
    private CourseDescriptionService courseDescriptionService;
    @Resource
    private ChapterService chapterService;
    @Resource
    private VideoService videoService;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insertCourseInfo(CourseInfoRequest courseInfoRequest) {
        EduCourse eduCourse = new EduCourse();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoRequest, eduCourse);
        eduCourseDescription.setDescription(courseInfoRequest.getDescription());
        if (!this.save(eduCourse)) {
            logger.info("课程信息添加失败");
            throw new EduException("课程信息添加失败");
        }
        eduCourseDescription.setId(eduCourse.getId());
        if (!courseDescriptionService.save(eduCourseDescription)) {
            logger.info("课程简介添加失败");
            throw new EduException("课程简介添加失败");
        }
        return eduCourse.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCourseInfo(CourseInfoRequest courseInfoRequest) {
        EduCourse eduCourse = new EduCourse();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoRequest, eduCourse);
        BeanUtils.copyProperties(courseInfoRequest, eduCourseDescription);
        if (!this.updateById(eduCourse) && courseDescriptionService.updateById(eduCourseDescription)) {
            logger.info("修改课程信息失败");
            throw new EduException("修改课程信息失败");
        }
        if (StringUtils.isEmpty(courseDescriptionService.getById(eduCourseDescription.getId()))) {
            if (!courseDescriptionService.save(eduCourseDescription)) {
                logger.info("修改课程简介");
                throw new EduException("修改课程简介");
            }
        }
    }

    @Override
    public CourseInfoRequest getCourseInfoBy(String id) {
        CourseInfoRequest courseInfoRequest = new CourseInfoRequest();
        EduCourse eduCourse = this.getById(id);
        EduCourseDescription eduCourseDescription = courseDescriptionService.getById(id);
        if (eduCourse != null) {
            BeanUtils.copyProperties(eduCourse, courseInfoRequest);
        }
        if (eduCourseDescription != null) {
            courseInfoRequest.setDescription(eduCourseDescription.getDescription());
        }
        return courseInfoRequest;
    }

    @Override
    public boolean getCourseConditionPageList(Page<EduCourse> eduCoursePage, CourseRequest courseRequest) {
        try {
            if (courseRequest == null) {
                baseMapper.selectPage(eduCoursePage, null);
                return true;
            }
            QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
            Field[] fields = courseRequest.getClass().getDeclaredFields();
            boolean bol = false;
            for (Field field : fields) {
                field.setAccessible(true);
                if (!StringUtils.isEmpty(field.get(courseRequest))) {
                    switch (field.getName()) {
                        case "parentId":
                            queryWrapper.eq("subject_parent_id", field.get(courseRequest));
                            break;
                        case "subjectId":
                            queryWrapper.eq("subject_id", field.get(courseRequest));
                            break;
                        case "beginTime":
                            queryWrapper.ge("gmt_create", field.get(courseRequest));
                            break;
                        case "endTime":
                            queryWrapper.le("gmt_create", field.get(courseRequest));
                            break;
                        case "minPrice":
                            queryWrapper.ge("price", field.get(courseRequest));
                            break;
                        case "maxPrice":
                            queryWrapper.le("price", field.get(courseRequest));
                            break;
                        case "title":
                            queryWrapper.like("title", field.get(courseRequest));
                    }
                    bol = true;
                }
            }
            if (bol) {
                baseMapper.selectPage(eduCoursePage, queryWrapper);
            } else {
                baseMapper.selectPage(eduCoursePage, null);
            }
            return true;
        } catch (IllegalAccessException e) {
            throw new EduException("课程组合条件查询失败");
        }

    }

    @Override
    public void removeCourseById(String id) {
        baseMapper.deleteById(id);
    }

    @Override
    public CourseInfo getAllCourseInfo(String courseId) {
        CourseInfo courseInfo = baseMapper.getCourseInfo(courseId);
        return courseInfo;
    }

    @Override
    public void deleteCourseById(String id) {
        courseDescriptionService.deleteDescriptionByCourseId(id);
        chapterService.deleteChapterByCourseId(id);
        videoService.deleteVideoByCourseId(id);
        baseMapper.deleteById(id);
    }

    @Override
    public Map<String, Object> listCoursePage(Page<EduCourse> pageCourse) {
        return null;
    }

    @Override
    public TeacherAllInfoDto getTeacherAllInfo(String id) {
        return null;
    }


}
