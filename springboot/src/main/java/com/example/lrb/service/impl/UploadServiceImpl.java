package com.example.lrb.service.impl;

import com.example.lrb.service.UploadService;
import com.example.lrb.utils.OssUtils;
import com.example.lrb.vo.JsonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Date: 2021/3/24 16:38
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private OssUtils ossUtils;

    @Override
    public JsonResult<String> uploadImage(MultipartFile file) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String originalFilename = file.getOriginalFilename();
        String ext = StringUtils.substringAfterLast(originalFilename, ".");
        String path = "images/" + sdf.format(new Date()) + "_" + System.currentTimeMillis() + "." + ext;

        path = ossUtils.uploadFile(path, file.getInputStream());
        return JsonResult.ok("上传成功", path);
    }

    @Override
    public JsonResult<String> uploadVideo(MultipartFile file) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String originalFilename = file.getOriginalFilename();
        String ext = StringUtils.substringAfterLast(originalFilename, ".");
        String path = "videos/" + sdf.format(new Date()) + "_" + System.currentTimeMillis() + "." + ext;

        path = ossUtils.uploadFile(path, file.getInputStream());
        return JsonResult.ok("上传成功", path);
    }
}
