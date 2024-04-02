package com.example.lrb.controller;

import com.example.lrb.service.UploadService;
import com.example.lrb.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Date: 2021/3/24 16:38
 */
@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("image")
    public JsonResult<String> uploadImage(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        return uploadService.uploadImage(file);
    }

    @PostMapping("video")
    public JsonResult<String> uploadVideo(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        return uploadService.uploadVideo(file);
    }
}
