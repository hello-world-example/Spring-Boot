package xyz.kail.demo.spring.boot.web.conf;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Getter
@Configuration
@ConfigurationProperties("xyz.kail.demo")
public class PropertiesConf {

    private Map<String, String> headers = new HashMap<>();
}
