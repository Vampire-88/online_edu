package com.online.school.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.school.common.result.JsonData;
import com.online.school.edu.entity.EduCourse;
import com.online.school.edu.entity.request.CourseInfoRequest;
import com.online.school.edu.entity.request.CourseRequest;
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

    /**
    * 功能描述 : TODO
    *
    * @param  null : TODO
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
     * 功能描述 : TODO
     *
     * @param  null : TODO
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
     * 功能描述 : TODO
     *
     * @param  null : TODO
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
     * 功能描述 : TODO
     *
     * @param  null : TODO
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
     * 功能描述 : TODO
     *
     * @param  null : TODO
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
     * 功能描述 : TODO
     *
     * @param  null : TODO
     * @return
     * @author 王威
     * @created 2020-01-14 16:50
     */
    @DeleteMapping("deleteCourse/{id}")
    public JsonData deleteCourse(@PathVariable String id){
        return JsonData.success();
    }

    /**
     * 功能描述 : TODO
     *
     * @param  null : TODO
     * @return
     * @author 王威
     * @created 2020-01-14 16:50
     */
    @PostMapping("updateCourseInfo")
    public JsonData updateCourseInfo(@Validated(value = {CourseInfoRequest.updateCourseInfoView.class}) @RequestBody CourseInfoRequest courseInfoRequest){
         courseService.updateCourseInfo(courseInfoRequest);
         return JsonData.success();
    }
}

