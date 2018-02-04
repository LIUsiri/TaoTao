package com.taotao.content.service;


import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * class_name: ContentService
 * package: com.taotao.content.service
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/25
 * creat_time: 19:20
 **/

public interface ContentService {
    /**
     *分页查询
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    public EasyUIDataGridResult getContentList(Long categoryId,Integer page,Integer rows);

    /**
     * 保存内容信息
     * @param content
     * @return
     */
    public TaotaoResult saveContent(TbContent content);

    /**
     * 根据categoryId 查询内容列表
     * @param categoryId
     * @return
     */
    public List<TbContent> getContentListByCategoryId(Long categoryId);
}
