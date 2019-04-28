package org.webapp.example.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Mark on 1/22/2017.
 */
// makes it a spring boot app.
// same as @Configuration @EnableAutoConfiguration @ComponentScan

// @Configuration
// identifies it as a spring application configuration file
//
// @EnableAutoConfiguration
// automatically scans for beans in the sub-packages, such as @Controller, @Configuration classes, etc.
// this will automatically pick up @Controllers in  org.webapp.example.school.web_controllers, for example.
//
// @ComponentScan
// Identifies this spring file as one that scans for components using a configuration (either manually by package,
// or via @EnableAutConfiguration
//
// Note: Here be dragons.
@SpringBootApplication
public class AppBootMain {

    public static void main(String[] args) {
        SpringApplication.run(AppBootMain.class, args);
    }
}
