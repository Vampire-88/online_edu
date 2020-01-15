package com.online.school.common.utils;

import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class FileUpLoadUtils {

    public static Boolean exceedSize(long size, int max) {
        return !(Math.ceil((double) size / max) <= 1);
    }

    public static String getFileName(MultipartFile file, String type) {
        String date = new DateTime().toString("yyyy/MM/dd");
        return date + "/" + type + "/" + UUID.randomUUID().toString() + file.getOriginalFilename();
    }
}
