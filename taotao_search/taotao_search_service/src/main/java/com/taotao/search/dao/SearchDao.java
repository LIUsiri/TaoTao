package com.taotao.search.dao;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * class_name: SearchDao
 * package: com.taotao.search.dao
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/30
 * creat_time: 20:57
 **/

@Repository
public class SearchDao {

    @Autowired
    private SolrServer solrServer;

    public SearchResult search(SolrQuery solrQuery)throws Exception{
        //根据query对象查询索引库
        QueryResponse response = solrServer.query(solrQuery);
        //取商品列表
        SolrDocumentList results = response.getResults();

        ArrayList<SearchItem> itemList = new ArrayList<>();
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

        for (SolrDocument solrDocument : results) {
            SearchItem item = new SearchItem();
            item.setId(Long.valueOf((String)solrDocument.get("id")));//域中的值是String  pojo中的是long 需要转换
            item.setCategory_name( solrDocument.get("item_category_name").toString());
            item.setImage((String) solrDocument.get("item_image"));
            item.setPrice((long) solrDocument.get("item_price"));
            item.setSell_point((String) solrDocument.get("item_sell_point"));
            //取高亮显示

            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle = "";
            //有高亮显示的内容时。
            if (list != null && list.size() >
                    0) {
                itemTitle = list.get(0);
            } else {
                itemTitle = (String) solrDocument.get("item_title");
            }
            item.setTitle(itemTitle);
            //添加到商品列表
            itemList.add(item);

        }
        SearchResult result = new SearchResult();
        //商品列表
        result.setItemList(itemList);
        //总记录数
        result.setRecordCount(results.getNumFound());
        return result;

    }
}
