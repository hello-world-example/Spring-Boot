package xyz.kail.demo.spring.boot.web.interceptor;

import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.kail.demo.spring.boot.web.conf.PropertiesConf;
import xyz.kail.demo.spring.boot.web.model.WebResult;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * OoooooooooK 判断来源
 */
@Component
public class DemoHandlerInterceptor implements HandlerInterceptor {

    @Resource
    private PropertiesConf conf;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("HandlerInterceptor.preHandle");
        for (Map.Entry<String, String> kv : conf.getHeaders().entrySet()) {
            if (!Objects.equals(request.getHeader(kv.getKey()), kv.getValue())) {
                response.setHeader("XX-Status-Code", "3099123");
                response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(WebResult.error("3099123", "来源错误，禁止访问")));
                writer.close();
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("HandlerInterceptor.postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("HandlerInterceptor.afterCompletion");
    }
}
