package com.online.school.edu.third;

import com.online.school.common.result.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient("online-school-video")
public interface VidClient {


    //@PathVariable一定要加参数名称
    @DeleteMapping("/vod/deleteVid/{videoId}")
    JsonData removeVideoById(@PathVariable("videoId") String videoId);

    @DeleteMapping("/vod/deleteMoreVid")
    JsonData deleteMoreVid(@RequestParam("videoIdList") List<String> videoIdList);
}
