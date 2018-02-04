package com.taotao.content.service;


import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.Cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * class_name: ContentCategoryService
 * package: com.taotao.content.service
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/25
 * creat_time: 19:20
 **/

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCategoryList(Long parentId) {

        //创建example,设置查询条件
        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        //执行查询 返回的List<TbContentCategory>
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        ArrayList<EasyUITreeNode> nodes = new ArrayList<>();
        for (TbContentCategory category : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(category.getId());
            node.setText(category.getName());
            node.setState(category.getIsParent()?"closed":"open");
            nodes.add(node);
        }
        return nodes;
    }

    @Override
    public TaotaoResult saveContentCategory(TbContentCategory contentCategory) {
        //补全属性
        contentCategory.setCreated(new Date());
        contentCategory.setIsParent(false);
        contentCategory.setSortOrder(1);
        contentCategory.setUpdated(contentCategory.getCreated());
        //判断如果新增的节点的父节点本身是一个叶子节点的话,需要更新成父节点
        TbContentCategory parent = tbContentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
        //说明插入的节点的父节点是一个叶子节点,要将他变成父节点
        if(!parent.getIsParent()){
            //变成父节点
            parent.setIsParent(true);
            tbContentCategoryMapper.updateByPrimaryKey(parent);
        }
        //执行插入
        int i = tbContentCategoryMapper.insertSelective(contentCategory);


        return TaotaoResult.ok(contentCategory);
    }
}
