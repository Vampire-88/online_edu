package com.online.school.edu.entity.enmu;

import lombok.Getter;

public enum EAliYunFileType {


    COURSE_COVER(1, "course/cover"),
    TEACHER_AVATAR(2, "teacher/avatar");

    @Getter
    private int value;
    @Getter
    private String message;

    EAliYunFileType(int value, String message) {
        this.value = value;
        this.message = message;
    }
    public static String getDesc(int value) {
        for (EAliYunFileType item : EAliYunFileType.values()) {
            if (item.value == value) {
                return item.message;
            }
        }
        return null;
    }
    public static EAliYunFileType parse(int value) {

        for (EAliYunFileType access : EAliYunFileType.values()) {
            if (access.value == value) {
                return access;
            }
        }
        return null;
    }
}
