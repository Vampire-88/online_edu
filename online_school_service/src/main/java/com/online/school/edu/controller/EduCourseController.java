package com.online.school.edu.controller;


import com.online.school.common.result.JsonData;
import com.online.school.edu.entity.EduCourse;
import com.online.school.edu.service.EduCourseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @PostMapping
    public JsonData addCourseInfo(@RequestBody EduCourse eduCourse){
        courseService.save(eduCourse);
        return JsonData.success();
    }

    @GetMapping("getCourseInfo/{id}")
    public JsonData getCourseInfo(@PathVariable String id){
        return JsonData.success();
    }
}

