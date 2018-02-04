package com.taotao.search.service;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;

import java.io.IOException;

public interface SearchItemService {
    //从数据库中查询所有的商品数据,然后使用solrj将数据导入到索引库中
    public TaotaoResult importAllIndex() throws Exception;

    /**
     * 将表现成传递过来的查询条件查询
     * @param queryString 主查询条件
     * @param page 当前页面
     * @param rows 每页显示的数量
     * @return SearchResult 封装好的数据
     */
    public SearchResult search(String queryString,Integer page,Integer rows) throws Exception;

    /**
     * 跟新索引库
     * @param searchItem
     */
    public void updateIndex(SearchItem searchItem) throws Exception;
}
