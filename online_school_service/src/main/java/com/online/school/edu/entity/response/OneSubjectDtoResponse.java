package com.online.school.edu.entity.response;

import com.online.school.edu.entity.TwoSubjectDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//用于表示一级分类
@Data
public class OneSubjectDtoResponse {

    private String id;//一级分类id
    private String title;//一级分类名称
    //一级分类所有的二级分类
    private List<TwoSubjectDto> children = new ArrayList<>();
}
