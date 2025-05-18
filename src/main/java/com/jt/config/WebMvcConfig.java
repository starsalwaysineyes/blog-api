package com.jt.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonViewResponseBodyAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.jt.common.interceptor.ClearTokenInterceptor;

import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(clearTokenInterceptor()).addPathPatterns("/**");

    }

    @Bean
    public ClearTokenInterceptor clearTokenInterceptor(){
        return new ClearTokenInterceptor();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){

        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();


        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect
        );

        List<MediaType> fastMediaTypes = new ArrayList<>();

        fastMediaTypes.add(MediaType.APPLICATION_JSON);

        fastConverter.setFastJsonConfig(fastJsonConfig);

        fastConverter.setSupportedMediaTypes(fastMediaTypes);

        converters.add(fastConverter);


    }

    @Bean
    public FastJsonViewResponseBodyAdvice fastJsonViewResponseBodyAdvice(){
        return new FastJsonViewResponseBodyAdvice();
    }

}
