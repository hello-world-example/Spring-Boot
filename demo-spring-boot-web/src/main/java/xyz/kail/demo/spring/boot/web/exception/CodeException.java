package xyz.kail.demo.spring.boot.web.exception;

public class CodeException extends Exception {

    private String code;

    public CodeException(String message, String code) {
        super(message);
        this.code = code;
    }

    public CodeException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }
}
