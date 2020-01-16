package com.online.school.edu.controller;


import com.online.school.common.result.JsonData;
import com.online.school.edu.entity.EduSubject;
import com.online.school.edu.service.SubjectService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-01-07
 */
@RestController
@RequestMapping("/service/subject")
@CrossOrigin
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    /**
     * 功能描述 : TODO
     *
     * @param  null : TODO
     * @return
     * @author 王威
     * @created 2020-01-14 16:50
     */
    @PostMapping("/importExcelSubject")
    public JsonData importExcelSubject(@RequestParam("file") MultipartFile file) {
        List<String> list = subjectService.importExcel(file);
        if (subjectService.importExcel(file).size() > 0) {
            return JsonData.error().message("部分数据导入失败").data("msgList", list);
        }
        return JsonData.success().message("开始读取数据");
    }

    /**
     * 功能描述 : TODO
     *
     * @param  null : TODO
     * @return
     * @author 王威
     * @created 2020-01-14 16:50
     */
    @GetMapping("/getAllSubjectDto")
    public JsonData getAllSubjectDto(){
        List list = subjectService.getAllSubjectDto();
        return JsonData.success().data("OneSubjectDto",list);
    }

    /**
     * 功能描述 : TODO
     *
     * @param  null : TODO
     * @return
     * @author 王威
     * @created 2020-01-14 16:50
     */
    @GetMapping("getSubjectById/{id}")
    public JsonData getSubjectById(@PathVariable String id){
        EduSubject eduSubject = subjectService.getById(id);
        return JsonData.success().data("eduSubject",eduSubject);
    }

    /**
     * 功能描述 : TODO
     *
     * @param  null : TODO
     * @return
     * @author 王威
     * @created 2020-01-14 16:50
     */
    @DeleteMapping("deleteSubjectById/{id}")
    public JsonData deleteSubjectById(@PathVariable String id){
        if(subjectService.deleteSubjectDto(id)){
            return JsonData.success();
        }
        return JsonData.error();
    }

    /**
     * 功能描述 : TODO
     *
     * @param  null : TODO
     * @return
     * @author 王威
     * @created 2020-01-14 16:50
     */
    @PostMapping("addLevel")
    public JsonData addOneLevel(@RequestBody EduSubject subject){
            if (subjectService.addSubject(subject)) {
                return JsonData.success();
            }
        return JsonData.error();
    }

}

