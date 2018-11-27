package no.itera.bloggingplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
}
