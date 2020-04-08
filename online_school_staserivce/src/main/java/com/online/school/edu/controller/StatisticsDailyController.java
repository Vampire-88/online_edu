package com.online.school.edu.controller;


import com.online.school.common.result.JsonData;
import com.online.school.edu.entity.request.CountDataRequest;
import com.online.school.edu.service.StatisticsDailyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-03-27
 */
@RestController
@RequestMapping("/edu/statistics")
@CrossOrigin
public class StatisticsDailyController {

    @Resource
    private StatisticsDailyService statisticsDailyService;

    @GetMapping("getStatisticDay/{day}")
    public JsonData getStatisticDay(@PathVariable String day){
        statisticsDailyService.getCountRegisterNum(day);
        return JsonData.success();
    }

    @PostMapping("getDataCount")
    public JsonData getDataCount(@RequestBody CountDataRequest countDataRequest){
         Map<String,Object> map=statisticsDailyService.getDataCount(countDataRequest);
         return JsonData.success().data("mapData",map);
    }
}

