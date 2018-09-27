package pers.husen.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * user: zhoufangchao
 * date: 2018/9/27
 */
@Controller
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public String helloWorld() {
        System.out.println("hello world");
        return "hello";
    }
}
