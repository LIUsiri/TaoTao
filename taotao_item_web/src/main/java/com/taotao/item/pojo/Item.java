package com.taotao.item.pojo;

import com.taotao.pojo.TbItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

/**
 * class_name: Item
 * package: com.taotao.item.pojo
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/2/4
 * creat_time: 12:41
 **/

public class Item extends TbItem{


    public Item(TbItem tbItem) {
        //只有相同的类型和相同的方法的时候,才会调用
        BeanUtils.copyProperties(tbItem,this);
    }

    public String[] getImages(){
        if(StringUtils.isNotBlank(super.getImage())){

            return super.getImage().split(",");

        }

        return null;
    }

}
