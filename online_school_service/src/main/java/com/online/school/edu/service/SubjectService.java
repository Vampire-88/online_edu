package com.online.school.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.online.school.edu.entity.Subject;
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
public interface SubjectService extends IService<Subject> {

    /**
    * 功能描述 : 读取excel文件内容
    *
    * @param  file : 文件
    * @return
    * @author 王威
    * @created 2020-01-07 13:59
    */
    List<String> importExcel(MultipartFile file);
}
