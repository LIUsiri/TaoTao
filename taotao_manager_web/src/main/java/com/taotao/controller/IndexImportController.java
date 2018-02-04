package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * class_name: IndexImportController
 * package: com.taotao.controller
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/30
 * creat_time: 17:34
 **/

@Controller
public class IndexImportController {

    @Autowired
    private SearchItemService searchItemService;

    @RequestMapping("/item/importAllIndex")
    @ResponseBody
    public TaotaoResult ImportIndex() throws Exception {
        searchItemService.importAllIndex();
        return TaotaoResult.ok();
    }
}
