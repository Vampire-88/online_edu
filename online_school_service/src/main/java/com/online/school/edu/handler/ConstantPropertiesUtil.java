package com.online.school.edu.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 王威
 * @version 1.0
 * @title 项目启动时，执行本类的初始化方法
 * @description
 * @created 2020-01-02 16:55
 * @changeRecord
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.keyId}")
    private String keyId;
    @Value("${aliyun.oss.keySecret}")
    private String keySecret;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    public static String ENDPOINT;
    public static String KEYID;
    public static String KEYSECRET;
    public static String BUCKETNAME;

    private static final Logger logger = LoggerFactory.getLogger(ConstantPropertiesUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endpoint;
        KEYID = keyId;
        KEYSECRET = keySecret;
        BUCKETNAME = bucketName;
        logger.info("---阿里云域名节点注入成功---");
    }
}
