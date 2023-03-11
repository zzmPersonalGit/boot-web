package com.example.bootweb.confing;

import com.example.bootweb.bean.Pet;
import com.example.bootweb.converter.GuiguMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.*;

import static org.springframework.util.StringUtils.hasLength;

/*
* 开启矩阵变量配置方法
* */
@Configuration
public class WebConfing implements WebMvcConfigurer {

    //方式一
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        //不移除；后面得内容，矩阵变量才可以生效
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }




    //方式二
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {


            /**
             * 自定义协商策略
             * @param configurer
             */
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                Map<String, MediaType> mediaTypeMap = new HashMap<>();
                mediaTypeMap.put("json",MediaType.APPLICATION_JSON);
                mediaTypeMap.put("xml",MediaType.APPLICATION_XML);
                mediaTypeMap.put("gg",MediaType.parseMediaType("application/x-guigu"));
                //指定支持解析哪些参数对应得媒体类型
                ParameterContentNegotiationStrategy parameterStrategy = new ParameterContentNegotiationStrategy(mediaTypeMap);

                HeaderContentNegotiationStrategy headeStrategy = new HeaderContentNegotiationStrategy();


                configurer.strategies(Arrays.asList(parameterStrategy,headeStrategy));
            }

            //配置内容协商功能
            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
                converters.add(new GuiguMessageConverter());
            }

            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }
        };
    }

    /**
     * 自定义类型转换器
     * @param registry 前端传递得联合参数 宠物：<input name="pet" value="啊闹,4">
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new Converter<String, Pet>() {
            @Override
            public Pet convert(String source) {
                if (!hasLength(source)){
                    Pet pet = new Pet();
                    //将传入得值通过逗号分割
                    String[] split = source.split(",");
                    pet.setName(split[0]);
                    pet.setAge(Integer.parseInt(split[1]));
                    return pet;
                }
                return null;
            }
        });
    }
}

