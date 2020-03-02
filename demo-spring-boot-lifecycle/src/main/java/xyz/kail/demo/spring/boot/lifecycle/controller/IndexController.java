package xyz.kail.demo.spring.boot.lifecycle.controller;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class IndexController {

    @Resource
    private Environment environment;

    @GetMapping("/")
    public String index(boolean fail) {
        if (fail) {
            throw new NullPointerException("fail");
        }
        System.out.println("index");
        return "index";
    }

    @ResponseBody
    @GetMapping("/env")
    public String env(String p) {
        if (StringUtils.isEmpty(p)) {
            return environment.toString();
        }

        "".hashCode();
        return environment.getProperty(p);
    }

}
