package com.online.school.edu.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.online.school.common.result.JsonData;
import com.online.school.edu.handler.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequestMapping("/service/oss")
@RestController
@CrossOrigin
public class FileUploadController {

    @PostMapping("upload")
    public JsonData uploadTeacherImg(@RequestParam MultipartFile file) {
        try {
            String date = new DateTime().toString("yyyy/MM/dd");
            String fileName = date+"/"+UUID.randomUUID().toString() + file.getOriginalFilename();
            InputStream in = file.getInputStream();
            // Endpoint以杭州为例，其它Region请按实际情况填写。
            String endpoint = ConstantPropertiesUtil.ENDPOINT;
            // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
            String accessKeyId = ConstantPropertiesUtil.KEYID;
            String accessKeySecret = ConstantPropertiesUtil.KEYSECRET;
            String BucketName = ConstantPropertiesUtil.BUCKETNAME;
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流。
            ossClient.putObject(BucketName, fileName, in);
            // 关闭OSSClient。
            ossClient.shutdown();
            String path = "http://" + BucketName + "." + endpoint + "/" + fileName;
            return JsonData.success().data("fileUrl", path);
        } catch (IOException e) {
            e.printStackTrace();
            return JsonData.error();
        }
    }

}
