package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class SrzBackendApplication {

  public static void main(final String[] args) {
    SpringApplication.run(SrzBackendApplication.class, args);
  }
}
