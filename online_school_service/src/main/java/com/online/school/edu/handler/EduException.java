package com.online.school.edu.handler;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class EduException extends RuntimeException{

    /**
    * 异常信息
    */
    private String message;
}
