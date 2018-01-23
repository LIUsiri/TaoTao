package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * class_name: ItemController
 * package: com.taotao.controller
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/23
 * creat_time: 20:33
 **/
@Controller
public class ItemController {


    @Autowired
    private ItemService itemService;


    @RequestMapping(value = "/item/list",method = RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page,Integer rows){
        //引入服务
        EasyUIDataGridResult list = itemService.getItemList(page, rows);
        //注入服务

        //调用服务

        return list;
    }
}
