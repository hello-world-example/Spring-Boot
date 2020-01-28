package xyz.kail.demo.spring.boot.lifecycle.conf;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnWebApplication
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
public class ErrorConf {

    @Bean
    @ConditionalOnMissingBean(CustomErrorController.class)
    public CustomErrorController exceptionController() {
        return new CustomErrorController();
    }

    /**
     * @see org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController
     */
    @Controller
    @RequestMapping("/error")
    public static class CustomErrorController implements ErrorController {

        @RequestMapping
        public ResponseEntity<Map<String, Object>> error() {
            Map<String, Object> body = new HashMap<>();
            body.put("code", "404");
            body.put("message", "访问地址不存在");
            return new ResponseEntity<>(body, HttpStatus.OK);
        }

        @Override
        public String getErrorPath() {
            return "/error";
        }
    }
}
