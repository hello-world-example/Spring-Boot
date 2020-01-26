# War 包部署

## pom packaging

```xml
<packaging>war</packaging>
```



## 继承 SpringBootServletInitializer

```java
/**
 * 需要继承 SpringBootServletInitializer
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        Application.run(LifecycleApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }
}
```



## 原理

> - [Java™ Servlet 3.1 Specification](https://javaee.github.io/servlet-spec/downloads/servlet-3.1/Final/servlet-3_1-final.pdf) > 8.2.4 Shared libraries / runtimes pluggability
>
> - [Java™ Servlet 3.1 规范](http://zhanjindong.com/assets/pdf/Servlet3.1-Specification.pdf) > 8.2.4 共享库 / 运行时可插拔性

- `javax.servlet.ServletContainerInitializer`
  - `spring-web` : `org.springframework.web.SpringServletContainerInitializer`

---

```java
@HandlesTypes(WebApplicationInitializer.class)
public class SpringServletContainerInitializer implements ServletContainerInitializer {

	@Override
	public void onStartup(@Nullable Set<Class<?>> webAppInitializerClasses, ServletContext servletContext) throws ServletException {
		...
  
		AnnotationAwareOrderComparator.sort(initializers);
		for (WebApplicationInitializer initializer : initializers) {
			initializer.onStartup(servletContext);
		}
	}

}
```

---



- `spring-web` : `org.springframework.web.WebApplicationInitializer`
    - `spring-boot` : `SpringBootServletInitializer`
    - `spring-web` : `AbstractContextLoaderInitializer`
      - `spring-webmvc` : `AbstractDispatcherServletInitializer `
          - `spring-webmvc` : `AbstractAnnotationConfigDispatcherServletInitializer`



## Read More

- [Servlet3.1规范翻译——注解和可插拔性](https://www.iteye.com/blog/jinnianshilongnian-1750736)