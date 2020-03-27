package com.online.school.edu.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VidService {

    String uploadVideoAl(MultipartFile file);

    void deleteVideoAl(String videoId);

    void deleteMoreVid(List<String> videoIdList);
}
