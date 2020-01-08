package com.online.school.edu.entity.response;

import lombok.Data;

//用于表示二级分类
@Data
public class TwoSubjectDtoResponse {

    private String id;//二级分类id
    private String title;//二级分类名称
}