package com.online.school.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.school.common.result.JsonData;
import com.online.school.edu.entity.EduCourse;
import com.online.school.edu.entity.request.CourseInfoRequest;
import com.online.school.edu.entity.request.CourseRequest;
import com.online.school.edu.entity.response.CourseInfo;
import com.online.school.edu.service.CourseService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

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
public class CourseController {

    @Resource
    private CourseService courseService;

    /**
    * 功能描述 : 全部课程信息
    *
    * @param
    * @return
    * @author 王威
    * @created 2020-01-14 16:50
    */
    @GetMapping("listCourse")
    public JsonData listCourse(){
        List<EduCourse> list = courseService.list(null);
        return JsonData.success().data("courseList",list);
    }

    /**
     * 功能描述 : 分页查询
     *
     * @param
     * @return
     * @author 王威
     * @created 2020-01-14 16:50
     */
    @GetMapping("coursePageList/{page}/{limit}")
    public JsonData getPageCourseList(@PathVariable long page,@PathVariable long limit){
        Page<EduCourse> eduCoursePage = new Page<>(page,limit);
        courseService.page(eduCoursePage,null);
        List<EduCourse> eduCoursePageRecords = eduCoursePage.getRecords();
        return JsonData.success().data("total",eduCoursePage.getTotal()).data("coursePageList",eduCoursePageRecords);
    }

    /**
     * 功能描述 : 条件分页查询
     *
     * @param
     * @return
     * @author 王威
     * @created 2020-01-14 16:50
     */
    @PostMapping("courseConditionPageList/{page}/{limit}")
    public JsonData getCourseConditionPageList(@PathVariable long page, @PathVariable long limit,@RequestBody(required = false) CourseRequest courseRequest) throws IllegalAccessException {
        Page<EduCourse> eduCoursePage = new Page<>(page,limit);
        courseService.getCourseConditionPageList(eduCoursePage,courseRequest);
        List<EduCourse> eduCoursePageRecords = eduCoursePage.getRecords();
        return JsonData.success().data("total",eduCoursePage.getTotal()).data("coursePageList",eduCoursePageRecords);
    }

    /**
     * 功能描述 : 添加课程信息
     *
     * @param
     * @return
     * @author 王威
     * @created 2020-01-14 16:50
     */
    @PostMapping("addCourseInfo")
    public JsonData addCourseInfo(@Validated @RequestBody CourseInfoRequest courseInfoRequest) {
        String courseId = courseService.insertCourseInfo(courseInfoRequest);
        return JsonData.success().data("courseId",courseId);
    }

    /**
     * 功能描述 : 根据课程id查询课程全部信息
     *
     * @param
     * @return
     * @author 王威
     * @created 2020-01-14 16:50
     */
    @GetMapping("getCourseInfo/{id}")
    public JsonData getCourseInfo(@PathVariable String id) {
        CourseInfoRequest course = courseService.getCourseInfoBy(id);
        return JsonData.success().data("courseInfoForm",course);
    }

    /**
     * 功能描述 : 根据课程id删除课程，描述，小结。
     *
     * @param
     * @return
     * @author 王威
     * @created 2020-01-14 16:50
     */
    @DeleteMapping("deleteCourse/{id}")
    public JsonData deleteCourse(@PathVariable String id){
        courseService.deleteCourseById(id);
        return JsonData.success();
    }

    /**
     * 功能描述 : 修改课程信息
     *
     * @param
     * @return
     * @author 王威
     * @created 2020-01-14 16:50
     */
    @PostMapping("updateCourseInfo")
    public JsonData updateCourseInfo(@Validated(value = {CourseInfoRequest.updateCourseInfoView.class}) @RequestBody CourseInfoRequest courseInfoRequest){
         courseService.updateCourseInfo(courseInfoRequest);
         return JsonData.success();
    }

    @GetMapping("getAllCourseInfo/{courseId}")
    public JsonData getAllCourseInfo(@PathVariable String courseId){
        CourseInfo courseInfo = courseService.getAllCourseInfo(courseId);
        return JsonData.success().data("courseInfo",courseInfo);
    }

    @PostMapping("updateCourseStatus/{courseId}")
    public JsonData updateCourseStatus(@PathVariable String courseId){
        EduCourse course = courseService.getById(courseId);
        if(course!=null) {
            course.setStatus("Normal");
            courseService.updateById(course);
            return JsonData.success();
        }
        return JsonData.error();
    }
}

