package com.online.school.edu.entity.enmu;

import lombok.Getter;

/**
* @author 王威
* @version 1.0
* @title 文件上传限制
* @description
* @created 2020-01-08 15:19
* @changeRecord
*/
public enum EFileUpLoadSize {

    MIN_KB(1024*50, "50KB"),
    MAX_KB(1024*500, "500KB"),
    MIN_MB(1024*1024, "1MB"),
    MAX_MB(1024*1024*50, "50MB");

    @Getter
    private int value;
    @Getter
    private String message;

    EFileUpLoadSize(int value, String message) {
        this.value = value;
        this.message = message;
    }
    public static String getDesc(int value) {
        for (EFileUpLoadSize item : EFileUpLoadSize.values()) {
            if (item.value == value) {
                return item.message;
            }
        }
        return null;
    }
    public static EFileUpLoadSize parse(int value) {

        for (EFileUpLoadSize access : EFileUpLoadSize.values()) {
            if (access.value == value) {
                return access;
            }
        }
        return null;
    }
}
