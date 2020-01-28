package xyz.kail.demo.spring.boot.lifecycle.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.HashMap;
import java.util.Map;

@ResponseBody
@ControllerAdvice(basePackages = "xyz.kail.demo")
public class BaseExceptionAdvice {

    private static Logger logger = LoggerFactory.getLogger(BaseExceptionAdvice.class);

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleException(Exception ex) {

        if (logger.isErrorEnabled()) {
            logger.error("内部错误", ex);
        }

        return new HashMap<String, String>() {
            {
                put("code", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
                put("message", "内部错误");
            }
        };
    }
}