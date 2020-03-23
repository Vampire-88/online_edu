package com.online.school.edu.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.online.school.edu.service.VidService;
import com.online.school.edu.utils.ConstantPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.online.school.edu.utils.ConstantPropertiesUtil.ACCESS_KEY_ID;
import static com.online.school.edu.utils.ConstantPropertiesUtil.ACCESS_KEY_SECRET;

@Service
public class VidServiceImpl implements VidService {

    private static final Logger logger = LoggerFactory.getLogger(VidServiceImpl.class);

    @Override
    public String uploadVideoAl(MultipartFile file) {
        String videoId = null;
        if(file != null) {
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            UploadStreamRequest request = null;
            try {
                request = new UploadStreamRequest(ACCESS_KEY_ID, ACCESS_KEY_SECRET, title, fileName, file.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
            if (response.isSuccess()) {
                videoId = response.getVideoId();
                logger.info("文件上传成功");
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                logger.error("上传失败",response.getCode(),response.getMessage());
            }
        }
        return videoId;
    }

    @Override
    public void deleteVideoAl(String videoId) {
        try {
            DefaultAcsClient client = initVodClient(ACCESS_KEY_ID,ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(videoId);
            DeleteVideoResponse response = client.getAcsResponse(request);
            logger.info("视频删除成功");
        } catch (ClientException e) {
            logger.error("视频删除失败");
        }
    }

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
