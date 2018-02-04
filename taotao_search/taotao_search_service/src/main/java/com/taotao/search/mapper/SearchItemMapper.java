package com.taotao.search.mapper;

import com.taotao.common.pojo.SearchItem;

import java.util.List;

/**
 * class_name: SearchItemMapper
 * package: com.taotao.search.mapper
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/30
 * creat_time: 17:02
 **/

    
public interface SearchItemMapper {

    public List<SearchItem> getSearchItemList();

    public SearchItem getSearchItemById(Long id);
}
