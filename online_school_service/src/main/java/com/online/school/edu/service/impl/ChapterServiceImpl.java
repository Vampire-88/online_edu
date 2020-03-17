package com.online.school.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.online.school.edu.entity.Chapter;
import com.online.school.edu.entity.response.ChapterDto;
import com.online.school.edu.mapper.ChapterMapper;
import com.online.school.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-16
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {


    @Override
    public List<ChapterDto> getChapterDtoByCourseId(String courseId) {
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        List<Chapter> chapterList = baseMapper.selectList(queryWrapper);
        chapterList.sort(Comparator.comparing(Chapter::getSort));
        List<ChapterDto> chapterDtoList = new ArrayList<>();
        chapterList.forEach(chapter -> {
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter,chapterDto);
            chapterDtoList.add(chapterDto);
        });

        return chapterDtoList;
    }
}
