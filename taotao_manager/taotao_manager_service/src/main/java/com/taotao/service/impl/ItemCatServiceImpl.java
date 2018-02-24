package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemCatService;
import com.taotao.service.manager.jedis.JedisClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * class_name: ItemCatServiceImpl
 * package: com.taotao.service.impl
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/24
 * creat_time: 15:57
 **/
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;


    @Override
    public List<EasyUITreeNode> getItemCatList(Long parentId) {
        //1.注入mapper
        //2.创建example
        TbItemCatExample example = new TbItemCatExample();
        //3.设置查询的条件
        example.createCriteria().andParentIdEqualTo(parentId);
        //4.执行查询 获取结果是list<tbitemCat>
        List<TbItemCat> tbItemCats = itemCatMapper.selectByExample(example);
        //5.转成List
        ArrayList<EasyUITreeNode> nodes = new ArrayList<>();
        for (TbItemCat itemCat : tbItemCats) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setText(itemCat.getName());
            node.setId(itemCat.getId());
            node.setState(itemCat.getIsParent()?"closed":"open");
            nodes.add(node);
        }
        return nodes;
    }

    @Autowired
    private JedisClient jedisClient;

    @Override
    public TbItemDesc getItemDesc(Long itemId) {

        try {
            if(StringUtils.isNotBlank(jedisClient.get("ITEM_INFO:"+itemId+":DESC"))){
                jedisClient.expire("ITEM_INFO:"+itemId+":DESC",3600*8);
                return JsonUtils.jsonToPojo(jedisClient.get("ITEM_INFO:"+itemId+":DESC"),TbItemDesc.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);

        try {
            jedisClient.set("ITEM_INFO:"+itemId+":DESC", JsonUtils.objectToJson(tbItemDesc));
            //设置有效期
            jedisClient.expire("ITEM_INFO:"+itemId+":DESC",3600*8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tbItemDesc;
    }


}
