package com.online.school.edu.controller;

import com.online.school.common.result.JsonData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/base")
//可以解决跨域问题，跨域即（端口，协议，ip其中一个不同就会有跨域问题）
@CrossOrigin
public class baseController {

    @GetMapping("info")
    public JsonData info(){
        return JsonData.success().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @PostMapping("login")
    public JsonData login(){
        return JsonData.success().data("token","admin");
    }

}
