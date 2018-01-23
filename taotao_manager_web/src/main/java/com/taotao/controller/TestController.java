package com.taotao.controller;

import com.taotao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * class_name: TestController
 * package: com.taotao.controller
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/23
 * creat_time: 16:30
 **/

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/getNow")
    @ResponseBody
    public String getNow(){

        return testService.getNow();
    }
}
