package com.online.school.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.school.common.result.JsonData;
import com.online.school.edu.entity.EduCourse;
import com.online.school.edu.entity.EduTeacher;
import com.online.school.edu.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

//前台讲师controller
@RestController
@RequestMapping("/service/frontTeacher")
@CrossOrigin
public class FrontEduTeacherController {

    @Resource
    private TeacherService teacherService;

    //查询讲师详情信息
    @GetMapping("{id}")
    public JsonData getTeacherInfoCourseId(@PathVariable String id) {
        //1 根据讲师id查询讲师详情信息，返回对象
        EduTeacher eduTeacher = teacherService.getById(id);

        //2 查询讲师所讲的课程，返回list集合
        List<EduCourse> courseList = teacherService.getCourseListByTeacherId(id);

        return JsonData.success().data("eduTeacher",eduTeacher).data("courseList",courseList);
    }

    //讲师列表的方法
    @GetMapping("{page}/{limit}")
    public JsonData getFrontTeacherListPage(@PathVariable Long page,
                                     @PathVariable Long limit) {
        //实现分页查询
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        //调用service的实现分页
        Map<String,Object> map = teacherService.getFrontTeacherList(pageTeacher);
        return JsonData.success().data(map);
    }
}
