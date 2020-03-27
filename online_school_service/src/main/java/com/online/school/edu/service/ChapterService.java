package com.online.school.edu.service;

import com.online.school.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.school.edu.entity.response.ChapterDto;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-16
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterDto> getChapterDtoByCourseId(String courseId);

    void deleteChapterByCourseId(String id);
}
