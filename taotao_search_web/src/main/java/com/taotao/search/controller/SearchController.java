package com.taotao.search.controller;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.service.SearchItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * class_name: SearchController
 * package: com.taotao.search.controller
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/30
 * creat_time: 22:11
 **/

@Controller
public class SearchController {

    private static final Integer ROWS =60;

    @Autowired
    private SearchItemService searchItemService;


    @RequestMapping("/search")
    public String search(@RequestParam(value = "q")String queryString, Integer page, Model model) throws Exception {

        if(StringUtils.isNotBlank(queryString)){
            queryString=new String(queryString.getBytes("iso-8859-1"),"utf-8");
        }

        SearchResult search = searchItemService.search(queryString, page, ROWS);

        model.addAttribute("query", queryString);
        //${totalPages}
        model.addAttribute("totalPages", search.getRecordCount());
        //${itemList} 商品的列表
        model.addAttribute("itemList", search.getItemList());
        //${page}
        model.addAttribute("page", search.getPage());

        return "search";

    }


}
