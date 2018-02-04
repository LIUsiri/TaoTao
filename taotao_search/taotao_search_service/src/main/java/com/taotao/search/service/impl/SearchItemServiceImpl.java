package com.taotao.search.service.impl;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.mapper.SearchItemMapper;
import com.taotao.search.service.SearchItemService;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.PAData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * class_name: SearchItemServiceImpl
 * package: com.taotao.search.service.impl
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/30
 * creat_time: 17:08
 **/


@Service
public class SearchItemServiceImpl implements SearchItemService {

    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private SearchDao searchDao;

    @Autowired
    private SolrServer solrServer;

    @Override
    public TaotaoResult importAllIndex() throws Exception {
        try {
            List<SearchItem> list = searchItemMapper.getSearchItemList();
            ArrayList<SolrInputDocument> documents = new ArrayList<>();
            for (SearchItem searchItem : list) {
                //1.将集合中的数据  一个个 sarchItem 放入document
                //2`创建一个文档对象SolrDocument
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id",searchItem.getId().toString());
                document.addField("item_title",searchItem.getTitle());
                document.addField("item_sell_point",searchItem.getSell_point());
                document.addField("item_category_name",searchItem.getCategory_name());
                document.addField("item_price",searchItem.getPrice());
                document.addField("item_desc",searchItem.getItem_desc());
                documents.add(document);
            }
            solrServer.add(documents);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return TaotaoResult.ok();
    }

    @Override
    public SearchResult search(String queryString, Integer page, Integer rows) throws Exception {
        //创建查询对象
        SolrQuery solrQuery = new SolrQuery();
        if(StringUtils.isNotBlank(queryString)) {
            solrQuery.setQuery(queryString);
        }else {
            solrQuery.setQuery("*:*");
        }
       /* 设置默认搜索域*/
        solrQuery.set("df","item_keywords");

        //分页的条件设置
        if(page==null)page=1;
        if (rows==null)rows=60;
        solrQuery.setStart((page-1)*rows);
        solrQuery.setRows(rows);


        //3.3 开启高亮 设置高亮显示的域 设置前缀和后缀
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
        solrQuery.setHighlightSimplePost("</em>");

        SearchResult search = searchDao.search(solrQuery);
        long pageCount = search.getRecordCount()/rows;
        if(search.getRecordCount()%rows>0){
            pageCount++;
        }
        search.setRecordCount(pageCount);
        return search;
    }

    @Override
    public void updateIndex(SearchItem searchItem) throws Exception {
        //跟新索引库
       // solrServer
        //2`创建一个文档对象SolrDocument
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id",searchItem.getId().toString());
        document.addField("item_title",searchItem.getTitle());
        document.addField("item_sell_point",searchItem.getSell_point());
        document.addField("item_category_name",searchItem.getCategory_name());
        document.addField("item_image",searchItem.getImage());
        document.addField("item_price",searchItem.getPrice());
        document.addField("item_desc",searchItem.getItem_desc());
        solrServer.add(document);
        solrServer.commit();
    }
}
