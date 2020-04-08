package com.online.school.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.school.common.result.JsonData;
import com.online.school.edu.entity.StatisticsDaily;
import com.online.school.edu.entity.enmu.CountType;
import com.online.school.edu.entity.request.CountDataRequest;
import com.online.school.edu.mapper.StatisticsDailyMapper;
import com.online.school.edu.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.school.edu.third.UcenterClient;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.RandomUtils;
import org.omg.CORBA.INTERNAL;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-03-27
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Resource
    private StatisticsDailyMapper statisticsDailyMapper;

    @Resource
    private UcenterClient ucenterClient;

    @Override
    public void getCountRegisterNum(String day) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);
        JsonData result = ucenterClient.countRegisterNum(day);
        Integer registerCount = (Integer)result.getData().get("registerCount");
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setDateCalculated(day);
        statisticsDaily.setRegisterNum(registerCount);
        statisticsDaily.setCourseNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setLoginNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setVideoViewNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setGmtCreate(new Date());
        statisticsDaily.setGmtModified(new Date());
        baseMapper.insert(statisticsDaily);
    }

    @Override
    public Map<String, Object> getDataCount(CountDataRequest countDataRequest) {
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("date_calculated",countDataRequest.getBegin());
        queryWrapper.le("date_calculated",countDataRequest.getEnd());
        List<StatisticsDaily> dailyList = baseMapper.selectList(queryWrapper);
        Map<String,Object> map = new HashMap<>();
        List<Integer> numList = new ArrayList<>();
        List<String> timeList = dailyList.stream().map(StatisticsDaily::getDateCalculated).collect(Collectors.toList());
        switch (CountType.parse(countDataRequest.getType()).getMessage()){
            case "login_num":
                numList = dailyList.stream().map(StatisticsDaily::getLoginNum).collect(Collectors.toList());
                break;
            case "register_num":
                numList = dailyList.stream().map(StatisticsDaily::getRegisterNum).collect(Collectors.toList());
                break;
            case "video_view_num":
                numList = dailyList.stream().map(StatisticsDaily::getVideoViewNum).collect(Collectors.toList());
                break;
            case "course_num":
                numList = dailyList.stream().map(StatisticsDaily::getCourseNum).collect(Collectors.toList());
                break;
        }
        map.put("timeList",timeList);
        map.put("dataList",numList);
        return map;
    }
}
