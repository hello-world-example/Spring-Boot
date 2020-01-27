package xyz.kail.demo.spring.boot.lifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 需要继承 SpringBootServletInitializer
 */
@SpringBootApplication
public class LifecycleApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LifecycleApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LifecycleApplication.class);
    }


}
