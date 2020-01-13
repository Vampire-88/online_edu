package com.online.school.edu.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.groups.Default;
import java.math.BigDecimal;

@Data
public class CourseInfoRequest {

    @ApiModelProperty(value = "课程ID")
    @NotEmpty(groups = {updateCourseInfoView.class}, message = "ID不能为空")
    private String id;

    @ApiModelProperty(value = "课程讲师ID")
    @NotEmpty(message = "教师不能为空")
    private String teacherId;

    @ApiModelProperty(value = "课程一级分类ID")
    @NotEmpty(message = "一级分类不能为空")
    private String subjectParentId;

    @ApiModelProperty(value = "课程二级分类ID")
    @NotEmpty(message = "二级分类不能为空")
    private String subjectId;

    @ApiModelProperty(value = "课程标题")
    @NotEmpty(message = "课程标题不能为空")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "课程简介")
    private String description;

    /**
     * 课程修改校验规则
     */
    public interface updateCourseInfoView extends Default {
    }
}
