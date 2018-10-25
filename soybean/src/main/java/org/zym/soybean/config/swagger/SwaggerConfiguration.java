package org.zym.soybean.config.swagger;

import com.sun.beans.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author zym
 * @date 18/5/13
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "soybean.swagger.enabled", havingValue = "true", matchIfMissing = true)
public class SwaggerConfiguration {
    @Autowired
    private Environment environment;

    @Value("${soybean.swagger.title:soybean}")
    private String title;

    @Value("${soybean.swagger.url:http://}")
    private String url;

    @Value("${soybean.swagger.version:1.0}")
    private String version;

    @Value("${soybean.swagger.description:soybean say hello world}")
    private String description;

    @Bean
    public Docket createRestApi() {
        String host = environment.getProperty("springfox.swagger2.host");
        return new Docket(DocumentationType.SWAGGER_2)
                .directModelSubstitute(LocalDate.class, java.util.Date.class)
                .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
                .host(host)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(url)
                .version(version)
                .build();
    }
}
