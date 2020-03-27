package com.online.school.edu.controller;


import com.online.school.common.result.JsonData;
import com.online.school.edu.service.StatisticsDailyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
public class StatisticsDailyController {

    @Resource
    private StatisticsDailyService statisticsDailyService;

    @GetMapping("getStatisticDay/{day}")
    public JsonData getStatisticDay(@PathVariable String day){
        statisticsDailyService.getCountRegisterNum(day);
        return JsonData.success();
    }
}

