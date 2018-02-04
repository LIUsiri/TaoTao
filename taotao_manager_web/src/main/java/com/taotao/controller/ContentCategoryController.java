package com.taotao.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * class_name: ContentCategoryController
 * package: com.taotao.controller
 * describe: 内容分类
 * @author: Liuxianglong
 * @date: 2018/1/27
 * creat_time: 16:48
 **/

@Controller
public class ContentCategoryController {

    @Autowired
    public ContentCategoryService contentCategoryService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public  List<EasyUITreeNode> showCategoryList(@RequestParam(value = "id",defaultValue = "0")Long parentId){

        List<EasyUITreeNode> list = contentCategoryService.getContentCategoryList(parentId);

        return list;
    }


    @RequestMapping("/content/category/create")
    @ResponseBody
    public TaotaoResult saveContenCatrgory(TbContentCategory category){

        return contentCategoryService.saveContentCategory(category);
    }

}