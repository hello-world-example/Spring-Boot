package xyz.kail.demo.spring.boot.web.interceptor;

import lombok.Builder;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;


@Component
@Deprecated
public class DemoWebRequestInterceptor implements WebRequestInterceptor {

    @Override
    public void preHandle(WebRequest request) throws Exception {
        System.out.println("WebRequestInterceptor.preHandle");
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        System.out.println("WebRequestInterceptor.postHandle");
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        System.out.println("WebRequestInterceptor.afterCompletion");
    }
}
