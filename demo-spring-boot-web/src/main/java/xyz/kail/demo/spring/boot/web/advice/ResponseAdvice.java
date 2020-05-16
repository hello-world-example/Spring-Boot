package xyz.kail.demo.spring.boot.web.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import xyz.kail.demo.spring.boot.web.model.WebResult;

import javax.servlet.http.HttpServletResponse;

/**
 * OoooooooooK 正常状态测试（包括异常 ExceptionAdvice）
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<WebResult<Object>> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getParameterType().isAssignableFrom(WebResult.class);
    }

    @Override
    public WebResult<Object> beforeBodyWrite(WebResult<Object> body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (null != body && response instanceof ServletServerHttpResponse) {
            ServletServerHttpResponse httpResponse = (ServletServerHttpResponse) response;
            HttpServletResponse servletResponse = httpResponse.getServletResponse();
            servletResponse.setHeader("XX-Status-Code", "test-" + body.getCode());
        }
        return body;
    }
}
