package xyz.kail.demo.spring.boot.web.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebResult<T> {

    private String code;

    private String message;

    private T result;

    public static <T> WebResult<T> success(T t) {
        WebResult<T> result = new WebResult<>();
        result.setCode("200");
        result.setResult(t);
        return result;
    }

    public static <T> WebResult<T> error(String code, String message) {
        WebResult<T> result = new WebResult<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
