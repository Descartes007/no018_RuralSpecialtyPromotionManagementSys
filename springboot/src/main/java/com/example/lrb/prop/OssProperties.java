package com.example.lrb.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Date: 2021/3/23 10:07
 */
@Component
@ConfigurationProperties(prefix = "aliyum.oss")
@Data
public class OssProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String bucketDomain;
    private String avatarSuffix;
}
