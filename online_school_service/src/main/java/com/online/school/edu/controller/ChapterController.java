package com.online.school.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.school.common.result.JsonData;
import com.online.school.edu.entity.Chapter;
import com.online.school.edu.entity.response.ChapterDto;
import com.online.school.edu.handler.EduException;
import com.online.school.edu.service.ChapterService;
import com.online.school.edu.service.VideoService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-01-16
 */
@RestController
@RequestMapping("/service/chapter")
@CrossOrigin
public class ChapterController {

    @Resource
    private ChapterService chapterService;
    @Resource
    private VideoService videoService;

    @GetMapping("getChapterVideoList/{courseId}")
    public JsonData getChapterVideoList(@PathVariable String courseId){
         List<ChapterDto> chapterDtoList = chapterService.getChapterDtoByCourseId(courseId);
         chapterDtoList = videoService.getVideoDtoByChapterDto(chapterDtoList);
         return JsonData.success().data("chapterDtoList",chapterDtoList);
    }

    @PostMapping("saveChapter")
    public JsonData saveChapter(@Validated @RequestBody Chapter chapter){
        chapterService.save(chapter);
        return JsonData.success();
    }

    @GetMapping("getChapterInfo/{chapterId}")
    public JsonData getChapterInfo(@PathVariable String chapterId){
        Chapter chapter = chapterService.getById(chapterId);
        return JsonData.success().data("chapter",chapter);
    }

    @DeleteMapping("deleteChapter/{chapterId}")
    public JsonData deleteChapter(@PathVariable String chapterId){
        if(videoService.countByChapterId(chapterId)>0) {
            throw new EduException("删除失败");
        }
        chapterService.removeById(chapterId);
        return JsonData.success();
    }

    @PostMapping("updateChapter")
    public JsonData updateChapter(@Validated @RequestBody Chapter chapter){
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",chapter.getId());
        chapterService.update(chapter,queryWrapper);
        return JsonData.success();
    }
}

