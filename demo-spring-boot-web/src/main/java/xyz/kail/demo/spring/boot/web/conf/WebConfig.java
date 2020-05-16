package xyz.kail.demo.spring.boot.web.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.kail.demo.spring.boot.web.interceptor.DemoHandlerInterceptor;
import xyz.kail.demo.spring.boot.web.interceptor.DemoWebRequestInterceptor;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private DemoWebRequestInterceptor demoWebRequestInterceptor;

    @Resource
    private DemoHandlerInterceptor demoHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addWebRequestInterceptor(demoWebRequestInterceptor);
        registry.addInterceptor(demoHandlerInterceptor);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    }
}
