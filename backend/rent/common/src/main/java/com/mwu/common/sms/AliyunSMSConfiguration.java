package com.mwu.common.sms;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "aliyun.sms.endpoint")
public class AliyunSMSConfiguration {
    @Value("${aliyun.sms.endpoint}")
    private String endpoint;
    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.sms.accessKeySecret}")
    private String accessKeySecret;

    @Bean
    public Client smsClient() {
        Config config = new Config();
        config.setEndpoint(endpoint);
        config.setAccessKeyId(accessKeyId);
        config.setAccessKeySecret(accessKeySecret);
        try{
            return new Client(config);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Aliyun SMS client", e);
        }

    }
}
