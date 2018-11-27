package no.itera.bloggingplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.datatype.guava.GuavaModule;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class BloggingPlatformApplication implements WebMvcConfigurer {

  public static void main(String[] args) {
    SpringApplication.run(BloggingPlatformApplication.class, args);
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedMethods("POST", "PUT", "DELETE", "GET", "OPTIONS")
        .allowedOrigins("http://localhost:9000");
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .apis(RequestHandlerSelectors.basePackage("no.itera.bloggingplatform.controller"))
        .paths(PathSelectors.ant("/**"))
        .build();
  }

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer customize() {
    return builder -> builder.modules(new GuavaModule());
  }
}
