package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class_name: IndexController
 * package: com.taotao.portal.controller
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/25
 * creat_time: 18:32
 **/

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String showIndex(){

        return "index";
    }
}
