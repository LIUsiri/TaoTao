package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService{

    public EasyUIDataGridResult getItemList(Integer page,Integer rows);

    /**
     * 保存商品的的基本信息,和商品的描述
     * @param tbItem 商品基本信息
     * @param desc 商品描述
     * @return 通用返回值
     */
    public TaotaoResult saveItemAndItemDesc(TbItem tbItem, String desc);

    /**
     * 给手机端调用信息
     * 根据用户的id返回用户的信息
     * @param id
     * @return Json
     */
    public TbItem getTbItemById(Long id);

    /**
     * 给手机用户更新商品的基本信息
     * @param item
     */
    void updateTbItem(TbItem item);

    /**
     * 给手机端调用删除商品的方法
     * @param id
     */
    void deleteById(Long id);
}
