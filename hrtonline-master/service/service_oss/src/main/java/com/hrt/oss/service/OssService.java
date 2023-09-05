package com.hrt.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface OssService {
    String uploadFileToOss(MultipartFile multipartFile,Long userId,String fileType);
    String uploadFileToOss(String fileName);
}
