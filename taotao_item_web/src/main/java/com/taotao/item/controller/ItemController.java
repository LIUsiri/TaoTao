package com.taotao.item.controller;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemCatService;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * class_name: ItemController
 * package: com.taotao.item.controller
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/2/4
 * creat_time: 12:22
 **/

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/item/{itemId}")
    public String showItemInfo(@PathVariable Long itemId, Model model){

        TbItem item = itemService.getTbItemById(itemId);
        TbItemDesc itemDesc = itemCatService.getItemDesc(itemId);

        Item item1 = new Item(item);

        model.addAttribute("itemDesc",itemDesc);
        model.addAttribute("item",item1);

        return "item";
    }

}
