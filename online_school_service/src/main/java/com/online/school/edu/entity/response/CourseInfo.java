package com.online.school.edu.entity.response;

import lombok.Data;

@Data
public class CourseInfo {
    
    private String id;

    private String title;

    private String cover;

    private String price;

    private String description;

    private String teacherName;

    private String levelOne;

    private String levelTwo;
}
