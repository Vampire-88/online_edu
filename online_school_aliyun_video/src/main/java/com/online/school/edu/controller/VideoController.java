package com.online.school.edu.controller;


import com.online.school.common.result.JsonData;
import com.online.school.edu.service.VidService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/vod")
@CrossOrigin
public class VideoController {

    @Resource
    private VidService vidService;

    @PostMapping("uploadVid")
    public JsonData uploadVid(MultipartFile file){
        String videoId = vidService.uploadVideoAl(file);
        return JsonData.success().data("videoId",videoId);
    }

    @DeleteMapping("deleteVid/{videoId}")
    public JsonData deleteVid(@PathVariable String videoId){
        vidService.deleteVideoAl(videoId);
        return JsonData.success();
    }

    @DeleteMapping("deleteMoreVid")
    public JsonData deleteMoreVid(@RequestParam("videoIdList") List<String> videoIdList){
         vidService.deleteMoreVid(videoIdList);
         return JsonData.success();
    }
}
