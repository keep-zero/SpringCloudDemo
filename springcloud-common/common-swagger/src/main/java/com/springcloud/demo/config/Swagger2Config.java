package com.springcloud.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description Swagger2的配置类
 * @auther: lai.guanfu
 * @date: 2019-05-06 19:18
 */
@EnableSwagger2 //开启swagger
@Configuration
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2) //
                .genericModelSubstitutes(DeferredResult.class) //
                .useDefaultResponseMessages(false) //
                .forCodeGeneration(true) //
                .apiInfo(apiInfo()) // 增加api相关信息
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select() // 通过select函数可控制选择哪些接口允许暴露给swagger展示
                .apis(RequestHandlerSelectors.basePackage("com.springcloud.demo.controller")) //指定扫描包进行接口展示限制
                .paths(PathSelectors.any()) //
                .build(); //
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder() //
                .title("springboot利用swagger构建api文档") //标题
                .description("简单优雅的restfun风格") //描述
                .termsOfServiceUrl("https://github.com/springfox/springfox-demos") //服务条款网址
                .version("1.0") //
                .build();
    }
}
