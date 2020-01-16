package com.online.school.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.school.common.result.JsonData;
import com.online.school.edu.entity.EduTeacher;
import com.online.school.edu.entity.request.TeacherRequest;
import com.online.school.edu.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-12-11
 */
@RestController
@RequestMapping("/service/teacher")
@CrossOrigin
public class TeacherController {

    @Resource
    private TeacherService eduTeacherService;

    /**
    * 功能描述 : 查询所有讲师
    *
    * @param
    * @return
    * @author 王威
    * @created 2019-12-11 12:56
    */
    @GetMapping("getAllTeacherList")
    public JsonData getAllTeacherList(){
        List<EduTeacher> eduTeacherList = eduTeacherService.list(null);
        return JsonData.success().data("items",eduTeacherList);
    }

    /**
    * 功能描述 : 分页查询所有教师
    *
    * @param
    * @return
    * @author 王威
    * @created 2019-12-17 16:01
    */
    @GetMapping("pageList/{page}/{limit}")
    public JsonData getPageTeacherList(@PathVariable long page,@PathVariable long limit){
        Page<EduTeacher> eduTeacherPage = new Page<>(page,limit);
        eduTeacherService.page(eduTeacherPage, null);
        List<EduTeacher> records = eduTeacherPage.getRecords();
        return JsonData.success().data("total",eduTeacherPage.getTotal()).data("items",records);
    }

    /**
    * 功能描述 : 条件分页查询教师
    *
    * @param
    * @return
    * @author 王威
    * @created 2019-12-17 16:00
    */
    @PostMapping("conditionPageList/{page}/{limit}")
    public JsonData getMoreConditionPageList(@PathVariable long page, @PathVariable long limit,@RequestBody(required = false) TeacherRequest teacherRequest){
        Page<EduTeacher> eduTeacherPage = new Page<>(page,limit);
        eduTeacherService.pageListCondition(eduTeacherPage,teacherRequest);
        long total = eduTeacherPage.getTotal();
        List<EduTeacher> records = eduTeacherPage.getRecords();
        return JsonData.success().data("total",total).data("items",records);
    }

    /**
    * 功能描述 : 新增教师
    *
    * @param  eduTeacher : 教师对象
    * @return
    * @author 王威
    * @created 2019-12-17 16:00
    */
    @PostMapping("addTeacher")
    public JsonData addTeacher(@RequestBody EduTeacher eduTeacher){
        if(eduTeacherService.save(eduTeacher)){
            return JsonData.success();
        }
        return JsonData.error();
    }

    /**
    * 功能描述 : 修改教师信息
    *
    * @param
    * @return
    * @author 王威
    * @created 2019-12-17 16:57
    */
    @PostMapping("updateTeacher")
    public JsonData updateTeacher(@RequestBody EduTeacher eduTeacher){
        if(eduTeacherService.updateById(eduTeacher)){
            return JsonData.success();
        }
        return JsonData.error();
    }

    /**
    * 功能描述 : 根据id查询教师
    *
    * @param
    * @return
    * @author 王威
    * @created 2019-12-17 16:51
    */
    @GetMapping("getTeacher/{id}")
    public JsonData getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return JsonData.success().data("eduTeacher",eduTeacher);
    }

    /**
    * 功能描述 : 根据id删除教师
    *
    * @param
    * @return
    * @author 王威
    * @created 2019-12-25 13:52
    */
    @DeleteMapping("/deleteTeacherById/{id}")
    public JsonData deleteTeacherById(@PathVariable String id){
        if(eduTeacherService.removeById(id)){
            return JsonData.success();
        }
        return JsonData.error();
    }
}

