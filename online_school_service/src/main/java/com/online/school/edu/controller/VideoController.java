package com.online.school.edu.controller;


import com.online.school.common.result.JsonData;
import com.online.school.edu.entity.Video;
import com.online.school.edu.service.VideoService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/service/video")
@CrossOrigin
public class VideoController {

    @Resource
    private VideoService videoService;

    @PostMapping("addVideo")
    public JsonData addVideo(@Validated @RequestBody Video video) {
        if (videoService.save(video)) {
            return JsonData.success();
        }
        return JsonData.error();
    }

    @GetMapping("getVideoInfo/{videoId}")
    public JsonData getVideoInfo(@PathVariable String videoId) {
        Video video = videoService.getById(videoId);
        return JsonData.success().data("video", video);
    }

    @PostMapping("updateVideo")
    public JsonData updateVideo(@Validated @RequestBody Video video){
        videoService.updateById(video);
        return JsonData.success();
    }

    @DeleteMapping("deleteVideo/{videoId}")
    public JsonData deleteVideo(@PathVariable String videoId){
        videoService.removeById(videoId);
        return JsonData.success();
    }

}

