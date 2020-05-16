package xyz.kail.demo.spring.boot.web.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import xyz.kail.demo.spring.boot.web.model.WebResult;

/**
 * OoooooooooK 异常状态测试
 */
@ResponseBody
@ControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(IllegalArgumentException.class)
    public WebResult<Object> handlerIllegalArgumentException(ServletWebRequest request, IllegalArgumentException ex) {
        return WebResult.error("5099123", ex.getMessage());
    }
}
