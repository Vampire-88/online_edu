package com.online.school.edu.entity.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChapterDto {

    private String id;

    private String title;

    private List<VideoDto> children = new ArrayList<>();
}
