package com.online.school.edu.service;

import org.springframework.web.multipart.MultipartFile;

public interface VidService {

    String uploadVideoAl(MultipartFile file);

    void deleteVideoAl(String videoId);
}
