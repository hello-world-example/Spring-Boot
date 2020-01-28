# Spring Boot 错误页面

> 官方文档 [7.1.11. Error Handling](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-error-handling)



## 静态错误页

```bash
src/
 +- main/
     +- resources/
         +- static/ # 静态资源路径
             +- error/
                 +- 404.html
                 +- 5xx.html # 支持 xx 通配
```

## 404 处理

```java
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
```

## 统一异常处理

> 404 不经过 ControllerAdvice

```java
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
```

