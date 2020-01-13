package com.online.school.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.school.edu.entity.EduCourse;
import com.online.school.edu.entity.EduCourseDescription;
import com.online.school.edu.entity.EduSubject;
import com.online.school.edu.entity.request.CourseInfoRequest;
import com.online.school.edu.handler.EduException;
import com.online.school.edu.mapper.EduCourseMapper;
import com.online.school.edu.service.EduCourseDescriptionService;
import com.online.school.edu.service.EduCourseService;
import com.online.school.edu.service.EduSubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-10
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    private static final Logger logger = LoggerFactory.getLogger(EduSubjectServiceImpl.class);

    @Resource
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Resource
    private EduSubjectService eduSubjectService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insertCourseInfo(CourseInfoRequest courseInfoRequest) {
        EduCourse eduCourse = new EduCourse();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoRequest,eduCourse);
        eduCourseDescription.setDescription(courseInfoRequest.getDescription());
        if (!this.save(eduCourse)) {
            logger.info("课程信息添加失败");
            throw new EduException("课程信息添加失败");
        }
        eduCourseDescription.setId(eduCourse.getId());
        if(!eduCourseDescriptionService.save(eduCourseDescription)){
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
        BeanUtils.copyProperties(courseInfoRequest,eduCourse);
        BeanUtils.copyProperties(courseInfoRequest,eduCourseDescription);
        if (!this.updateById(eduCourse)&&eduCourseDescriptionService.updateById(eduCourseDescription)) {
            logger.info("修改课程信息失败");
            throw new EduException("修改课程信息失败");
        }
        if(StringUtils.isEmpty(eduCourseDescriptionService.getById(eduCourseDescription.getId()))){
            eduCourseDescriptionService.save(eduCourseDescription);
        }
    }

    @Override
    public CourseInfoRequest getCourseInfoBy(String id) {
        CourseInfoRequest courseInfoRequest = new CourseInfoRequest();
        EduCourse eduCourse = this.getById(id);
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(id);
        if(eduCourse!=null) {
            EduSubject subject = eduSubjectService.getById(eduCourse.getSubjectId());
            BeanUtils.copyProperties(eduCourse, courseInfoRequest);
            if(subject!=null) {
                if (!subject.getParentId().equals("0")) {
                    courseInfoRequest.setSubjectParentId(subject.getParentId());
                } else {
                    courseInfoRequest.setSubjectId("");
                    courseInfoRequest.setSubjectParentId(eduCourse.getSubjectId());
                }
            }
        }
        if(eduCourseDescription!=null) {
            courseInfoRequest.setDescription(eduCourseDescription.getDescription());
        }
        return courseInfoRequest;
    }


}
