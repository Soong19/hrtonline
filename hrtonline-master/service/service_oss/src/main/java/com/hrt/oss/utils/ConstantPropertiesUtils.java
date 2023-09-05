package com.hrt.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtils implements InitializingBean {

    //读取配置文件内容
    @Value(value = "${aliyun.oss.file.endpoint}")
    private String endPoint;

    @Value(value = "${aliyun.oss.file.keyid}")
    private String keyId;

    @Value(value = "${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value(value = "${aliyun.oss.file.bucketname}")
    private String bucketName;

    public static String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT=endPoint;
        KEY_ID=keyId;
        KEY_SECRET=keySecret;
        BUCKET_NAME=bucketName;

    }
}
