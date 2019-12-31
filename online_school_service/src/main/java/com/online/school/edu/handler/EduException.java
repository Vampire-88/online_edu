package com.online.school.edu.handler;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class EduException extends RuntimeException{

    /**
    * 状态码
    */
    private Integer code;

    /**
    * 异常信息
    */
    private String message;
}
