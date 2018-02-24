package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

import java.util.List;

public interface ItemCatService {
    /**
     * 根据父节点的ID查询该父节点下的所有的子节点的列表
     * @param parentId
     * @return
     */
    public List<EasyUITreeNode> getItemCatList(Long parentId);


    /**
     * 根据商品Id查询商品描述
     * @param itemId
     * @return TbItemDesc
     */
   public TbItemDesc getItemDesc(Long itemId);

}
