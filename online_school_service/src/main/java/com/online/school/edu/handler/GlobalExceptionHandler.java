package com.online.school.edu.handler;

import com.online.school.common.result.JsonData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @author 王威
* @version 1.0
* @title 异常统一处理
* @description
* @company
* @mobile
* @created 2019-12-17 17:42
* @changeRecord
*/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonData error(Exception e){
       e.printStackTrace();
       return JsonData.error().message("出现了异常");
    }

    @ExceptionHandler(EduException.class)
    @ResponseBody
    public JsonData error(EduException e){
        e.printStackTrace();
        return JsonData.error().message("出现了异常");
    }
}
