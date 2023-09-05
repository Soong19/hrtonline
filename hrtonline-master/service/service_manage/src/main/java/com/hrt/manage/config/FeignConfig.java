package com.hrt.manage.config;

import com.aliyun.oss.common.utils.HttpHeaders;
import com.netflix.ribbon.RequestTemplate;
import feign.RequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class FeignConfig implements RequestInterceptor {
    @Override
    public void apply(feign.RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        requestTemplate.header(HttpHeaders.AUTHORIZATION, token);
    }

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;
    @Bean
    public Encoder feignFormEncoder () {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }

}

