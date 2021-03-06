package com.online.school.edu.entity.request;

import lombok.Data;

/**
* @author 王威
* @version 1.0
* @title TODO
* @description 讲师条件查询请求参数
*
* @mobile 18149292591
* @created 2019-12-12 14:26
* @changeRecord
*/
@Data
public class TeacherRequest {

    /**
    * 讲师姓名
    */
    private String name;

    /**
    * 讲师头衔
    */
    private String level;

    /**
    * 开始时间
    */
    private String begin;

    /**
    * 结束时间
    */
    private String end;
}
