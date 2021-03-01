package xyz.kail.demo.spring.boot.web.controller;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.kail.demo.spring.boot.web.model.WebResult;

import java.lang.reflect.Array;
import java.util.Arrays;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/success/json")
    public WebResult<String> successJson() {
        return WebResult.success("ok");
    }

    @RequestMapping("/success/string")
    public String successString() {
        return "ok";
    }

    @RequestMapping("/error/json")
    public WebResult<String> errorJson() {
        Assert.isTrue(false, "出错了");

        return WebResult.success("error");
    }

    /**
     * @see org.springframework.http.HttpStatus
     */
    @RequestMapping("/empty")
    public String empty() {
        return "";
    }

}
