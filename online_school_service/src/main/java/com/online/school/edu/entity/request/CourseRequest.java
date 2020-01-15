package com.online.school.edu.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseRequest {

    @ApiModelProperty("一级分类ID")
    private String parentId;

    @ApiModelProperty("二级分类ID")
    private String subjectId;

    @ApiModelProperty("最早创建时间")
    private String beginTime;

    @ApiModelProperty("最晚创建时间")
    private String endTime;

    @ApiModelProperty("最低价格")
    private BigDecimal minPrice;

    @ApiModelProperty("最高价格")
    private BigDecimal maxPrice;

    @ApiModelProperty("标题名称")
    private String title;
}
