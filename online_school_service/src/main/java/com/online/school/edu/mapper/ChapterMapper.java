package com.online.school.edu.mapper;

import com.online.school.edu.entity.Chapter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.online.school.edu.entity.response.ChapterDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-01-16
 */
public interface ChapterMapper extends BaseMapper<Chapter> {

}
