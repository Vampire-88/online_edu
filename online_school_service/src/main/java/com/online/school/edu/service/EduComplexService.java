package com.online.school.edu.service;

import com.online.school.edu.entity.enmu.EAliYunFileType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EduComplexService {

    String UpLoadFile(MultipartFile file, EAliYunFileType aliYunFileType) throws IOException;
}
