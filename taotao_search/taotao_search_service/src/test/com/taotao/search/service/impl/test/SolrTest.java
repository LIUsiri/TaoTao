package com.taotao.search.service.impl.test;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * class_name: SolrTest
 * package: com.taotao.search.service.impl.test
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/30
 * creat_time: 16:33
 **/
public class SolrTest {


    @Test
    public void test()throws Exception{
        //1`创建一个solrServer,使用httpSolrServer创建对象
        SolrServer server = new HttpSolrServer("http://120.79.187.159:8080/solr");
        //2`创建一个文档对象SolrDocument
        SolrInputDocument document = new SolrInputDocument();
        //3`向文档中天剑域,必须要有id域,域的名字必须在schema.xml中定义
        document.addField("id","test02");
        document.addField("item_title", "测试商品");
        document.addField("item_price", "199");
        // 第五步：把文档添加到索引库中。
        server.add(document);
        // 第六步：提交。
        server.commit();
    }

    @Test
    public void test2() throws IOException, SolrServerException {
        //1`创建一个solrServer,使用httpSolrServer创建对象
        SolrServer server = new HttpSolrServer("http://120.79.187.159:8080/solr");
        server.deleteById("change.me");
        server.commit();
    }
    @Test
    public void deleteDocumentByQuery() throws Exception {
        SolrServer solrServer = new HttpSolrServer("http://120.79.187.159:8080/solr");
        solrServer.deleteByQuery("item_price:199");
        solrServer.commit();
    }

    @Test
    public void queryDocument() throws Exception {
        // 第一步：创建一个SolrServer对象
        SolrServer solrServer = new HttpSolrServer("http://120.79.187.159:8080/solr");
        // 第二步：创建一个SolrQuery对象。
        SolrQuery query = new SolrQuery();
        // 第三步：向SolrQuery中添加查询条件、过滤条件。。。
        query.setQuery("*:*");
        // 第四步：执行查询。得到一个Response对象。
        QueryResponse response = solrServer.query(query);
        // 第五步：取查询结果。
        SolrDocumentList solrDocumentList = response.getResults();
        System.out.println("查询结果的总记录数：" + solrDocumentList.getNumFound());
        // 第六步：遍历结果并打印。
        for (SolrDocument solrDocument : solrDocumentList) {
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_price"));
        }
    }

    @Test
    public void queryDocumentWithHighLighting() throws Exception {
        // 第一步：创建一个SolrServer对象
        SolrServer solrServer = new HttpSolrServer("http://120.79.187.159:8080/solr");
        // 第二步：创建一个SolrQuery对象。
        SolrQuery query = new SolrQuery();
        // 第三步：向SolrQuery中添加查询条件、过滤条件。。。
        query.setQuery("手机");
        //指定默认搜索域
        query.set("df", "item_keywords");
        //开启高亮显示
        query.setHighlight(true);
        //高亮显示的域
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em>");
        query.setHighlightSimplePost("</em>");
        // 第四步：执行查询。得到一个Response对象。
        QueryResponse response = solrServer.query(query);
        // 第五步：取查询结果。
        SolrDocumentList solrDocumentList = response.getResults();
        System.out.println("查询结果的总记录数：" + solrDocumentList.getNumFound());
        // 第六步：遍历结果并打印。
        for (SolrDocument solrDocument : solrDocumentList) {
            System.out.println(solrDocument.get("id"));
            //取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle = null;
            if (list != null && list.size() > 0) {
                itemTitle = list.get(0);
            } else {
                itemTitle = (String) solrDocument.get("item_title");
            }
            System.out.println(itemTitle);
            System.out.println(solrDocument.get("item_price"));
        }
    }





}
