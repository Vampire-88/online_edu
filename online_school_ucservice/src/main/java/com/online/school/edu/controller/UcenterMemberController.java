package com.online.school.edu.controller;


import com.online.school.common.result.JsonData;
import com.online.school.edu.service.UcenterMemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-03-27
 */
@RestController
@RequestMapping("/edu/member")
public class UcenterMemberController {


    @Resource
    private UcenterMemberService ucenterMemberService;

    @GetMapping("countRegisterNum/{day}")
    public JsonData countRegisterNum(@PathVariable String day){
        Integer result = ucenterMemberService.countRegisterNum(day);
        return JsonData.success().data("registerCount",result);
    }
}

