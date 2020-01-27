# main 方法启动过程



## SpringApplication 构造方法

```java
public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
    ...
    // 通过 SpringFactories 方式获取 ApplicationContextInitializer
    // 允许在 ApplicationContext refresh 之前，对其实例做进一步的设置和处理
    setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
    // 通过 SpringFactories 方式获取 ApplicationListener 的实现，接收事件
    setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
    // 获取启动的类
    this.mainApplicationClass = deduceMainApplicationClass();
}

private Class<?> deduceMainApplicationClass() {
    ...
    StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
    for (StackTraceElement stackTraceElement : stackTrace) {
      // 遍历调用栈，获取第一个 main 方法
      if ("main".equals(stackTraceElement.getMethodName())) {
        return Class.forName(stackTraceElement.getClassName());
      }
    }
    ...
}
```

## run 方法

```java
public ConfigurableApplicationContext run(String... args) {
  ...
  // SpringFactories 方式获取 SpringApplicationRunListener
  // ❤❤❤ 实现必须包含构造函数 public Xxx(SpringApplication application, String[] args)
  SpringApplicationRunListeners listeners = getRunListeners(args);
  
  // 调用 SpringApplicationRunListener 的 starting 方法
  // EventPublishingRunListener 发布 ApplicationStartingEvent 事件
  listeners.starting();
  try {
    ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
    // 调用 SpringApplicationRunListener 的 environmentPrepared 方法
    // EventPublishingRunListener 发布 ApplicationEnvironmentPreparedEvent 事件
    ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
    configureIgnoreBeanInfo(environment);
    // 
    Banner printedBanner = printBanner(environment);
    context = createApplicationContext();
    exceptionReporters = getSpringFactoriesInstances(SpringBootExceptionReporter.class, new Class[] { ConfigurableApplicationContext.class }, context);
    // ❤❤❤ 调用 ApplicationContextInitializer#initialize(context)
    // 
    // 调用 SpringApplicationRunListener 的 contextPrepared  方法
    // EventPublishingRunListener 发布 ApplicationContextInitializedEvent 事件
    // 
    // 调用 SpringApplicationRunListener 的 contextLoaded  方法
    // EventPublishingRunListener 发布 ApplicationPreparedEvent 事件
    prepareContext(context, environment, listeners, applicationArguments, printedBanner);
    refreshContext(context);
    afterRefresh(context, applicationArguments);

    ...
      
    // 调用 SpringApplicationRunListener 的 started  方法
    // EventPublishingRunListener 发布 ApplicationStartedEvent 事件
    listeners.started(context);
    
    // ❤❤❤ 从 context 中获取 ApplicationRunner 和 CommandLineRunner，并调用 ❤❤❤
    callRunners(context, applicationArguments);
  }
  catch (Throwable ex) {
    // 调用 SpringApplicationRunListener 的 failed  方法
    // EventPublishingRunListener 发布 ApplicationFailedEvent 事件
    handleRunFailure(context, ex, exceptionReporters, listeners);
    throw new IllegalStateException(ex);
  }

  try {
    // 调用 SpringApplicationRunListener 的 running  方法
    // EventPublishingRunListener 发布 ApplicationReadyEvent 事件
    listeners.running(context);
  }
  catch (Throwable ex) {
    // 调用 SpringApplicationRunListener 的 failed  方法
    // EventPublishingRunListener 发布 ApplicationFailedEvent 事件
    handleRunFailure(context, ex, exceptionReporters, null);
    throw new IllegalStateException(ex);
  }
  return context;
}
```

## SpringBootServletInitializer 方式

```java
package org.springframework.boot.web.servlet.support;
...
public abstract class SpringBootServletInitializer implements WebApplicationInitializer {
  ...
    
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		...
		WebApplicationContext rootAppContext = createRootApplicationContext(servletContext);
		...
	}

	protected WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
    // SpringApplication 构建器，设置默认配置
		SpringApplicationBuilder builder = createSpringApplicationBuilder();
		builder.main(getClass());
		...
		builder.initializers(new ServletContextApplicationContextInitializer(servletContext));
		builder.contextClass(AnnotationConfigServletWebServerApplicationContext.class);
    
    // ❤❤❤ 自定义扩展配置
		builder = configure(builder);
    
		builder.listeners(new WebEnvironmentPropertySourceInitializer(servletContext));
		SpringApplication application = builder.build();
		...
		// Ensure error pages are registered
		...
    // 调用 SpringApplication#run
		return run(application);
	}
	...
}

```



## Read More

- **[聊聊 Spring Boot 中的那些生命周期和其中的可扩展点](https://blog.csdn.net/Dlxi12345/article/details/93518342)**
- 
- [SpringBoot扩展点之一：SpringApplicationRunListener](https://www.cnblogs.com/duanxz/p/11243271.html)
- [SpringBoot之ApplicationContextInitializer的理解和使用](https://www.cnblogs.com/hello-shf/p/10987360.html)