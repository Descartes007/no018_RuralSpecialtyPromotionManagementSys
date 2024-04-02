package com.example.lrb.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.lrb.prop.OssProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @Date: 2021/3/23 10:46
 */
@Component
@Slf4j
public class OssUtils {

    @Autowired
    private OssProperties ossProperties;

    /**
     * 上传文件
     *
     * @param path
     * @param inputStream
     */
    public String uploadFile(String path, InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            OSS ossClient = new OSSClientBuilder().build(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
            ossClient.putObject(ossProperties.getBucketName(), path, inputStream);
            ossClient.shutdown();
            inputStream.close();
            return ossProperties.getBucketDomain() + path;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("==文件上传失败==:" + path);
        }
        return null;
    }


}
