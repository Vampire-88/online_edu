package com.online.school.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.online.school.edu.entity.EduSubject;
import com.online.school.edu.entity.response.OneSubjectDtoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-07
 */
public interface SubjectService extends IService<EduSubject> {

    /**
     * 功能描述 : 读取excel文件内容
     *
     * @param file : 文件
     * @return
     * @author 王威
     * @created 2020-01-07 13:59
     */
    List<String> importExcel(MultipartFile file);

    /**
     * 功能描述 : 获取课程分级树状结构信息
     *
     * @param
     * @return
     * @author 王威
     * @created 2020-01-08 16:14
     */
    List<OneSubjectDtoResponse> getAllSubjectDto();

    /**
     * 功能描述 : 删除课程分类
     *
     * @param
     * @return
     * @author 王威
     * @created 2020-01-10 10:17
     */
    Boolean deleteSubjectDto(String id);

    /**
     * 功能描述 : 添加分类
     *
     * @param subject : linked{EduSubject}
     * @return
     * @author 王威
     * @created 2020-01-10 11:19
     */
    Boolean addSubject(EduSubject subject);
}
