package com.online.school.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.school.common.result.JsonData;
import com.online.school.edu.entity.EduCourse;
import com.online.school.edu.entity.response.ChapterDto;
import com.online.school.edu.entity.response.TeacherAllInfoDto;
import com.online.school.edu.service.ChapterService;
import com.online.school.edu.service.CourseService;
import com.online.school.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

//前台课程controller
@RestController
@RequestMapping("/service/frontCourse")
@CrossOrigin
public class FrontEduCourseController {

    @Resource
    private CourseService courseService;

    @Resource
    private ChapterService chapterService;

    @Resource
    private VideoService videoService;

    //课程分页列表的方法
    @GetMapping("{page}/{limit}")
    public JsonData getCourseListPage(@PathVariable Long page,
                                      @PathVariable Long limit) {
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.listCoursePage(pageCourse);
        return JsonData.success().data(map);
    }

    //根据课程id查询课程详情信息
    @GetMapping("{id}")
    public JsonData getCourseInfoAll(@PathVariable String id) {
        //1 根据课程id查询信息，包含课程基本信息，课程描述，讲师信息，分类信息
        TeacherAllInfoDto courseInfoDto = courseService.getTeacherAllInfo(id);

        //2 根据课程id查询课程里面所有的章节，章节里面所有小节
        List<ChapterDto> chapterVideoList = chapterService.getChapterDtoByCourseId(id);
        chapterVideoList = videoService.getVideoDtoByChapterDto(chapterVideoList);
        
        return JsonData.success().data("courseInfo",courseInfoDto).data("chapterVideoList",chapterVideoList);
    }
}
