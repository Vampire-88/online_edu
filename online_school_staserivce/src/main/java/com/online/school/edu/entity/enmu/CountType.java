package com.online.school.edu.entity.enmu;

import lombok.Getter;

public enum CountType {

    LOGIN(1, "login_num"),
    REGISTER(2, "register_num"),
    VIDEO(3,"video_view_num"),
    COURSE(4,"course_num");

    @Getter
    private int value;
    @Getter
    private String message;

    CountType(int value, String message) {
        this.value = value;
        this.message = message;
    }
    public static String getDesc(int value) {
        for (CountType item : CountType.values()) {
            if (item.value == value) {
                return item.message;
            }
        }
        return null;
    }
    public static CountType parse(int value) {

        for (CountType access : CountType.values()) {
            if (access.value == value) {
                return access;
            }
        }
        return null;
    }
}
