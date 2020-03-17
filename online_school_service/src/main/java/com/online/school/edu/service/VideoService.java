package com.online.school.edu.service;

import com.online.school.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.school.edu.entity.response.ChapterDto;
import com.online.school.edu.entity.response.VideoDto;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-16
 */
public interface VideoService extends IService<Video> {

    List<ChapterDto> getVideoDtoByChapterDto(List<ChapterDto> chapterDtoList);

    int countByChapterId(String chapterId);
}
