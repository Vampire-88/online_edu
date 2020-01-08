package com.online.school.edu.controller;


import com.online.school.common.result.JsonData;
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

    @PostMapping("/importExcelSubject")
    public JsonData importExcelSubject(@RequestParam("file") MultipartFile file) {
        List<String> list = subjectService.importExcel(file);
        if (subjectService.importExcel(file).size() > 0) {
            return JsonData.error().message("部分数据导入失败").data("msgList", list);
        }
        return JsonData.success().message("开始读取数据");
    }

    @GetMapping()
    public JsonData getAllSubjectDto(){
        return JsonData.success();
    }
}

