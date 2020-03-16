package com.online.school.edu.controller;


import com.online.school.common.result.JsonData;
import com.online.school.edu.entity.Chapter;
import com.online.school.edu.entity.response.ChapterDto;
import com.online.school.edu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/edu/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("getChapterVideoListCourseId")
    public JsonData getChapterVideoListCourseId(@PathVariable String courseId){
         List<ChapterDto> list = chapterService.getChapterDtoByCourseId(courseId);
         return JsonData.success();
    }

}

