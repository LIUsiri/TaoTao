package com.taotao.controller;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * class_name: ItemInterfaceController
 * package: com.taotao.controller
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/31
 * creat_time: 20:39
 **/

@Controller
public class ItemInterfaceController {

    @Autowired
    private ItemService itemService;

    //根据商品Id查询商品
    //根据状态来返回,并携带具体要的参数
    @RequestMapping(value = "/rest/taoto/interface/{id}",method = RequestMethod.GET)
    public ResponseEntity<TbItem> getItemById(@PathVariable Long id){

        try {
            TbItem item = itemService.getTbItemById(id);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            e.printStackTrace();
            //返回的实500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    // 新增商品
    // url: /rest/item/interface
    // method: POST
    @RequestMapping(value = "/rest/item/interface",method = RequestMethod.POST)
    public ResponseEntity<TbItem> saveItem(TbItem item,TbItemDesc desc) {

        try {
            String itemDesc = desc.getItemDesc();
            itemService.saveItemAndItemDesc(item, itemDesc);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
    // 修改商品
    // url: /rest/item/interface
    // method: PUT
    @RequestMapping(value = "/rest/item/interface",method = RequestMethod.PUT)
    public ResponseEntity<Void> updateItem(TbItem item){

        try {
            itemService.updateTbItem(item);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            //修改成功不显示任何信息
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    // 删除商品
    // url: /rest/item/interface/{id}
    // method: DELETE
    @RequestMapping(value = "/rest/item/interface/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        try {
            itemService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }


}
