package com.online.school.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.school.edu.entity.Video;
import com.online.school.edu.entity.response.ChapterDto;
import com.online.school.edu.entity.response.VideoDto;
import com.online.school.edu.mapper.VideoMapper;
import com.online.school.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-16
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Override
    public List<ChapterDto> getVideoDtoByChapterDto(List<ChapterDto> chapterDtoList) {
        chapterDtoList.forEach(chapterDto -> {
            QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("chapter_id",chapterDto.getId());
            List<Video> videoList = baseMapper.selectList(queryWrapper);
            if(!videoList.isEmpty()) {
                List<VideoDto> videoDtoList = new ArrayList<>();
                videoList.forEach(video -> {
                    VideoDto videoDto = new VideoDto();
                    BeanUtils.copyProperties(video,videoDto);
                    videoDtoList.add(videoDto);
                });
                chapterDto.setChildren(videoDtoList);
            }
        });
        return chapterDtoList;
    }

    @Override
    public int countByChapterId(String chapterId) {
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id",chapterId);
        return baseMapper.selectList(queryWrapper).size();
    }
}
