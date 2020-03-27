package com.online.school.edu.third;

import com.online.school.common.result.JsonData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("online-school-ucservice")
@Component
public interface UcenterClient {

    @GetMapping("/edu/member/countRegisterNum/{day}")
    JsonData countRegisterNum(@PathVariable("day") String day);
}
