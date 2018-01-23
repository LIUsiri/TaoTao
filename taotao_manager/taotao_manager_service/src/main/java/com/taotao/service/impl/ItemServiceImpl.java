package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * class_name: ItemServiceImpl
 * package: com.taotao.service.impl
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/23
 * creat_time: 20:21
 **/

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {

        PageHelper.startPage(page, rows);

        List<TbItem> tbItems = tbItemMapper.selectByExample(null);
        //构建分页对象,里面包含了总记录数
        PageInfo<TbItem> info =new PageInfo(tbItems);
        long total = info.getTotal();

        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(tbItems);
        result.setTotal(total);
        return result;
    }
}
