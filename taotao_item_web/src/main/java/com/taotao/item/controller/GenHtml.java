package com.taotao.item.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * class_name: GenHtml
 * package: com.taotao.item.controller
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/2/5
 * creat_time: 21:03
 **/

@Controller
public class GenHtml {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @RequestMapping("/genHTML")
    public String gen() throws IOException, TemplateException {
        Configuration configuration = freeMarkerConfigurer.getConfiguration();

        Template template = configuration.getTemplate("templatetest.ftl");

        Map<Object, Object> map = new HashMap<>();
        map.put("key","hello");
        //创建流对象
        FileWriter fileWriter = new FileWriter("F:\\CRM_image\\123.HTML");
        template.process(map,fileWriter);
        fileWriter.close();

        return "ok";
    }

}
