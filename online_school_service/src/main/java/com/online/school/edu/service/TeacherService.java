package com.online.school.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.school.edu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.school.edu.entity.request.TeacherRequest;

/**
* @author 王威
* @version 1.0
* @title TODO
* @description 讲师服务类
*
* @mobile 18149292591
* @created 2019-12-12 14:31
* @changeRecord
*/
public interface TeacherService extends IService<EduTeacher> {

    void pageListCondition(Page<EduTeacher> eduTeacherPage, TeacherRequest teacherRequest);
}
