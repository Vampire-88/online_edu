package com.online.school.edu.controller;


import com.online.school.common.result.JsonData;
import com.online.school.edu.entity.EduCourse;
import com.online.school.edu.entity.request.CourseInfoRequest;
import com.online.school.edu.service.EduCourseService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-01-10
 */
@RestController
@RequestMapping("/service/course")
@CrossOrigin
public class EduCourseController {

    @Resource
    private EduCourseService courseService;

    @GetMapping("listCourse")
    public JsonData listCourse(){
        List<EduCourse> list = courseService.list(null);
        return JsonData.success().data("courseList",list);
    }

    @PostMapping("addCourseInfo")
    public JsonData addCourseInfo(@Validated @RequestBody CourseInfoRequest courseInfoRequest) {
        String courseId = courseService.insertCourseInfo(courseInfoRequest);
        return JsonData.success().data("courseId",courseId);
    }

    @GetMapping("getCourseInfo/{id}")
    public JsonData getCourseInfo(@PathVariable String id) {
        CourseInfoRequest course = courseService.getCourseInfoBy(id);
        return JsonData.success().data("courseInfoForm",course);
    }

    @DeleteMapping("deleteCourse/{id}")
    public JsonData deleteCourse(@PathVariable String id){
        return JsonData.success();
    }

    @PostMapping("updateCourseInfo")
    public JsonData updateCourseInfo(@Validated(value = {CourseInfoRequest.updateCourseInfoView.class}) @RequestBody CourseInfoRequest courseInfoRequest){
         courseService.updateCourseInfo(courseInfoRequest);
         return JsonData.success();
    }
}

