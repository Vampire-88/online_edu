package com.online.school.edu.service;

import com.online.school.edu.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.school.edu.entity.request.CountDataRequest;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-03-27
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void getCountRegisterNum(String day);

    Map<String, Object> getDataCount(CountDataRequest countDataRequest);
}
