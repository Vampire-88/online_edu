package com.online.school.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.school.edu.entity.EduTeacher;
import com.online.school.edu.entity.request.TeacherRequest;
import com.online.school.edu.mapper.EduTeacherMapper;
import com.online.school.edu.service.EduTeacherService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2019-12-11
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    /**
    * 功能描述 : 组合条件查询讲师
    *
    * @param  eduTeacherPage : 分页条件
    * @param  teacherRequest : 讲师类型条件参数
    * @return
    * @author 王威
    * @created 2019-12-12 14:32
    */
    @Override
    public void pageListCondition(Page<EduTeacher> eduTeacherPage, TeacherRequest teacherRequest) throws IllegalAccessException {
        if(null == teacherRequest ){
            baseMapper.selectPage(eduTeacherPage,null);
            return;
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        Field[] fields = teacherRequest.getClass().getDeclaredFields();
        Boolean bol = false;
        for(Field field:fields){
            field.setAccessible(true);
            if(!StringUtils.isEmpty(field.get(teacherRequest))){
                switch (field.getName()){
                    case "name":
                        queryWrapper.like("name",field.get(teacherRequest));
                        break;
                    case "level":
                        queryWrapper.eq("level",field.get(teacherRequest));
                        break;
                    case "begin":
                        queryWrapper.ge("gmt_create",field.get(teacherRequest));
                        break;
                    case "end":
                        queryWrapper.le("gmt_create",field.get(teacherRequest));
                }
                bol = true;
            }
        }
        if(bol) {
            baseMapper.selectPage(eduTeacherPage, queryWrapper);
        }else {
            baseMapper.selectPage(eduTeacherPage,null);
        }

    }
}
