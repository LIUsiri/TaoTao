package com.taotao.portal.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.Ad1Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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

    @Value("${CATEGORY_ID}")
    private Long CATEGORY_ID;

    @Value("${AD1_HEIGHT}")
    private String AD1_HEIGHT;

    @Value("${AD1_HEIGHTB}")
    private String AD1_HEIGHTB;

    @Value("${AD1_WIDTH}")
    private String AD1_WIDTH;

    @Value("${AD1_WIDTHB}")
    private String AD1_WIDTHB;


    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model){
        //需要查询首页的轮播图的数据展示给页面
        //引入服务
        //调用服务
        List<TbContent> list = contentService.getContentListByCategoryId(CATEGORY_ID);
        //转换成list
        ArrayList<Ad1Node> nodels = new ArrayList<>();
        for (TbContent content : list) {
            Ad1Node node = new Ad1Node();
            node.setAlt(content.getSubTitle());
            node.setHeight(AD1_HEIGHT);
            node.setHeightB(AD1_HEIGHTB);
            node.setHref(content.getUrl());
            node.setSrc(content.getPic());
            node.setSrcB(content.getPic2());
            node.setWidth(AD1_WIDTH);
            node.setWidthB(AD1_WIDTHB);
            nodels.add(node);
        }
        //数据装换成JSON传递给页面
        String json = JsonUtils.objectToJson(nodels);
        model.addAttribute("ad1",json);

        return "index";
    }


}
