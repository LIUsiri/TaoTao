package com.taotao.content.service;


import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;

import java.util.List;

/**
 * class_name: ContentCategoryService
 * package: com.taotao.content.service
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/25
 * creat_time: 19:20
 **/

public interface ContentCategoryService {
    //查询所有节点
    public List<EasyUITreeNode>  getContentCategoryList(Long parentId);
    //插入内容分类记录
    public TaotaoResult saveContentCategory(TbContentCategory contentCategory);



}
