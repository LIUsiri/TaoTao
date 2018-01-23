package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class_name: IndexController
 * package: com.taotao.controller
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/23
 * creat_time: 17:50
 **/


@Controller
public class IndexController {

    @RequestMapping("/")
    public String showIndex(){

        return "index";
    }

    @RequestMapping("/{page}")
    public String itemList(@PathVariable String page){

        return page;
    }
}
