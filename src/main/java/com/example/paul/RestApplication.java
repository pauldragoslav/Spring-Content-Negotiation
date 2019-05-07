package com.example.paul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Configuration
public class RestApplication implements WebMvcConfigurer {

   public static void main(String[] args) {
      SpringApplication.run(RestApplication.class, args);
   }

   public void configureContentNegotiation(
         ContentNegotiationConfigurer contentNegotiationConfigurer) {
      contentNegotiationConfigurer
            .favorPathExtension(false)
            .favorParameter(false)
            .parameterName("mediaType")
            .ignoreAcceptHeader(false)
            .useJaf(false)
            .defaultContentType(MediaType.APPLICATION_JSON)
            .mediaType("application/xml", MediaType.APPLICATION_XML)
            .mediaType("application/json", MediaType.APPLICATION_JSON);
   }
}
