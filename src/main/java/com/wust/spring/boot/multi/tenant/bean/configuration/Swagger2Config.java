package com.wust.spring.boot.multi.tenant.bean.configuration;

import com.wust.spring.boot.multi.tenant.bean.annotation.Api_Base;
import com.wust.spring.boot.multi.tenant.bean.annotation.Api_Business;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${swagger2.enable:true}")
    private boolean enable;
//两者都可以
    /**
     * 配置swagger2核心配置docket </br>
     * swagger访问路径：http://localhost:8888/swagger-ui.html </br>
     * swagger业务接口api
     *
     * @return
     */
    /**
     * Knife4j 的前身就是swagger。在swagger添加的依赖基础上，新增knife4j自己的依赖。
     * 打开浏览器访问：http://localhost:8888/doc.html 。
     **/
    @Bean
    public Docket restApiBusiness() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("业务分组接口")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api_Business.class))
                .paths(PathSelectors.any())
                .build()
                .enable(enable);
    }

    @Bean
    public Docket restApiBase() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("基础接口")
                .apiInfo(apiInfoBase())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api_Base.class))
                .paths(PathSelectors.any())
                .build()
                .enable(enable);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("业务分组接口")
                .contact(new Contact("zhudayang", "http://www.wust.edu.cn/", "13476046278@163.com"))
                .description("zhudayang测试Swagger")
                .version("1.0.0")
                .termsOfServiceUrl("http://www.wust.edu.cn/")
                .build();
    }

    private ApiInfo apiInfoBase() {
        return new ApiInfoBuilder()
                .title("基础接口")
                .contact(new Contact("zhudayang", "http://www.wust.edu.cn/", "13476046278@163.com"))
                .description("zhudayang测试Swagger")
                .version("1.0.0")
                .termsOfServiceUrl("http://www.wust.edu.cn/")
                .build();
    }
}