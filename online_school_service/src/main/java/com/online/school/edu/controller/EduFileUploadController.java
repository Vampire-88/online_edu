package com.online.school.edu.controller;

import com.online.school.common.result.JsonData;
import com.online.school.common.utils.FileUpLoadUtils;
import com.online.school.edu.entity.enmu.EAliYunFileType;
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

    /**
     * 功能描述 : TODO
     *
     * @param  null : TODO
     * @return
     * @author 王威
     * @created 2020-01-14 16:50
     */
    @PostMapping("upload")
    public JsonData uploadTeacherImg(@RequestParam("file") MultipartFile file,@RequestParam("type") Integer type){
        try {
            if(!FileUpLoadUtils.exceedSize(file.getSize(),EFileUpLoadSize.MIN_MB.getValue())) {
                String path = complexService.UpLoadFile(file, EAliYunFileType.parse(type));
                return JsonData.success().data("fileUrl", path).data("message","上传成功");
            }
            return JsonData.success().data("message","文件大小超出限制");
        } catch (IOException e) {
            return JsonData.error().data("message","上传失败");
        }

    }

}
