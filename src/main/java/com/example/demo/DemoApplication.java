package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;

@Slf4j
@SpringBootApplication
@Import({BeanValidatorPluginsConfiguration.class})
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class DemoApplication {

  public static void main(final String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}
