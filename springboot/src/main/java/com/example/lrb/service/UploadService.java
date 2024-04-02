package com.example.lrb.service;

import com.example.lrb.vo.JsonResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Date: 2021/3/24 16:38
 */

public interface UploadService {

    JsonResult<String> uploadImage(MultipartFile file) throws IOException;

    JsonResult<String> uploadVideo(MultipartFile file) throws IOException;
}
