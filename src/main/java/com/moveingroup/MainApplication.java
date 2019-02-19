package com.moveingroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@EnableAutoConfiguration
public class MainApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }
}
