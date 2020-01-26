# 热部署

> -  [“How-to” Guides](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto) > [15. Hot Swapping](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-hotswapping)
>
> - [Using Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html) > [8. Developer Tools](https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-devtools)



## 依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
```

## 默认配置

```properties
spring.freemarker.cache=false

spring.devtools.restart.poll-interval=1s
spring.devtools.restart.quiet-period=400ms
spring.devtools.restart.exclude=META-INF/maven/**,META-INF/resources/**,resources/**,static/**,public/**,templates/**,**/*Test.class,**/*Tests.class,git.properties,META-INF/build-info.properties
# 根据指定的文件触发
spring.devtools.restart.trigger-file=

spring.devtools.livereload.enabled=true
```

## IDEA 设置

devtools 是检查 classpath（编译后的文件、target 目录） 下文件变化，所以修改文件后需要手动或者自动重新编译。

IDEA **手动编译**比较简单，右键修改的文件选择 **“Build Module xxx”** 或 **“Recompile xxx”** 即可。



自动编译设置比较复杂，步骤如下

- 双击 **Shift**， 搜索 `Maintenance` 回车
- 选择 `Registry...`
- 找到 `compiler.automake.allow.when.app.running`， 进行勾选



## LiveReload

LiveReload 是个自动刷线页面的工具，如果内容有变化会自动刷新页面。

Spring Boot 启动时默认暴露 LiveReload 端口，可以在启动日志中找到

```bash
...
... o.s.b.d.a.OptionalLiveReloadServer: LiveReload server is running on port 35729
...
```

通过安装 Chrome 插件获取，插件市场搜索 `LiveReload` 即可（好久没更新了），或者访问 [此链接](https://chrome.google.com/webstore/detail/livereload/jnihajbhpnppcggbcgedagnkighmdlei)。

安装后插件按钮默认是 空心的，点击变成 实心 打开 LiveReload，可以从 Chrome 调试控制台查看 LiveReload 建立的 Websocket 链接。



## Read More

- [Spring DevTools 介绍](https://blog.csdn.net/isea533/article/details/70495714)
- [spring-boot 速成(2) devtools之热部署及LiveReload](https://www.cnblogs.com/yjmyzz/p/use-devtools-of-spring-boot-framework.html)
- [Spring Boot Developer Tools and LiveReload](https://dzone.com/articles/spring-boot-developer-tools-and-live-reload)



