package com.online.school.edu.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.online.school.common.utils.FileUpLoadUtils;
import com.online.school.edu.handler.ConstantPropertiesUtil;
import com.online.school.edu.service.EduComplexService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service("complexService")
public class EduComplexServiceImpl implements EduComplexService {

    @Override
    public String UpLoadFile(MultipartFile file) throws IOException {
        String fileName = FileUpLoadUtils.getFileName(file);
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
        return "http://" + BucketName + "." + endpoint + "/" + fileName;
    }
}
