# 静态资源

> [Spring Boot Features](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html) > [ 7.1.5. Static Content](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-spring-mvc-static-content)



## 查找顺序

- `classpath:/META-INF/resources` > [Java™ Servlet Specification](https://javaee.github.io/servlet-spec/downloads/servlet-3.1/Final/servlet-3_1-final.pdf) 
- `classpath:/resources`
- **`classpath:/static`**
- `classpath:/public`



## 默认配置

```properties
spring.mvc.static-path-pattern=/**

spring.resources.static-locations=classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/
```



## webjars 格式

> 官网： https://www.webjars.org/
>
> 作用： 依赖 jar 包，直接使用静态文件

以 jquery 的 jar 包格式举例

```bash
META-INF/resources/webjars/jquery/3.4.1/jquery.min.map
META-INF/resources/webjars/jquery/3.4.1/jquery.min.js
META-INF/resources/webjars/jquery/3.4.1/jquery.slim.js
META-INF/resources/webjars/jquery/3.4.1/jquery.js
META-INF/resources/webjars/jquery/3.4.1/webjars-requirejs.js
META-INF/resources/webjars/jquery/3.4.1/jquery.slim.min.js
META-INF/resources/webjars/jquery/3.4.1/jquery.slim.min.map
```

可通过 http://localhost:8080/webjars/jquery/3.4.1/jquery.js 直接访问 jar 包内的静态文件

**去掉访问路径中的版本号** （ 即访问 http://localhost:8080/webjars/jquery/jquery.js ），需要添加以下依赖：

```xml
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>webjars-locator-core</artifactId>
    <version>${x.x.x}</version>
</dependency>
```

原理 @see

- `spring-webmvc` : `org.springframework.web.servlet.resource.ResourceResolver`
    - `spring-webmvc` : `org.springframework.web.servlet.resource.AbstractResourceResolver`
        - `spring-webmvc` : `org.springframework.web.servlet.resource.WebJarsResourceResolver`

## Read More

- [ 7. Developing Web Applications](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-developing-web-applications)
- Webjars
  - [Instructions for Servlet 3](https://www.webjars.org/documentation#servlet3)
  - [Instructions for Spring MVC](https://www.webjars.org/documentation#springmvc)
  - [Instructions for Spring Boot](https://www.webjars.org/documentation#springboot)

