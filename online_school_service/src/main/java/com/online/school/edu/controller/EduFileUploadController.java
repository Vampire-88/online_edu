package com.online.school.edu.controller;

import com.online.school.common.result.JsonData;
import com.online.school.common.utils.FileUpLoadUtils;
import com.online.school.edu.entity.enmu.EFileUpLoadSize;
import com.online.school.edu.service.EduComplexService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RequestMapping("/service/oss")
@RestController
@CrossOrigin
public class EduFileUploadController {

    @Resource
    EduComplexService complexService;

    @PostMapping("upload")
    public JsonData uploadTeacherImg(@RequestParam MultipartFile file){
        try {
            if(!FileUpLoadUtils.exceedSize(file.getSize(),EFileUpLoadSize.MIN_KB.getValue())) {
                String path = complexService.UpLoadFile(file);
                return JsonData.success().data("fileUrl", path).data("message","上传成功");
            }
            return JsonData.success().data("message","文件大小超出限制");
        } catch (IOException e) {
            return JsonData.error().data("message","上传失败");
        }

    }

}
