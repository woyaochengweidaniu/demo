package com.example.project.demo.config.swagger;

import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lcm
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

  @Value("${swagger.show}")
  private boolean swaggerShow;

  @Bean
  public Docket createRestApi() {
    Predicate<RequestHandler> predicate = input -> {
      if (input.isAnnotatedWith(ApiOperation.class)) {
        return true;
      }
      return false;
    };
    return new Docket(DocumentationType.SWAGGER_2)
        .enable(swaggerShow)
        .apiInfo(apiInfo())
        .select()
        .apis(predicate)
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo () {
      return new ApiInfoBuilder()
          .title("综合档案接口文档")
          .description("所有接口集合")
          .version("1.0")
          .build();
    }
  }