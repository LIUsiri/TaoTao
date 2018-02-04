package com.taotao.content.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * class_name: ContentService
 * package: com.taotao.content.service
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/25
 * creat_time: 19:20
 **/

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public EasyUIDataGridResult getContentList(Long categoryId, Integer page, Integer rows) {

        //创建example
        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        //开始分页
        PageHelper.startPage(page,rows);
        //执行查询
        List<TbContent> list = contentMapper.selectByExample(example);
        //设置分页信息
        PageInfo<TbContent> info = new PageInfo<>(list);
        //封装到EasyUIDataGridResult里面
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(info.getTotal());
        result.setRows(info.getList());

        return result;
    }

    @Override
    public TaotaoResult saveContent(TbContent content) {
        //补全属性
        content.setCreated(new Date());
        content.setUpdated(content.getCreated());
        //插入
         contentMapper.insertSelective(content);
         //缓存同步
        jedisClient.hdel(CONTENT_KEY, content.getCategoryId().toString());

        return TaotaoResult.ok();
    }

    @Autowired
    private JedisClient jedisClient;

    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;

    @Override
    public List<TbContent> getContentListByCategoryId(Long categoryId) {
        //注意：添加缓存 不能影响的正常的业务逻辑


        //判断 是否有缓存 ，如果有，直接返回缓存中的数据到页面

        try {
            String stringjson = jedisClient.hget(CONTENT_KEY, categoryId+"");
            if(StringUtils.isNotBlank(stringjson)){
                System.out.println("有缓存啦.................");
                //不为空，说明找到缓存数据
                List<TbContent> list = JsonUtils.jsonToList(stringjson, TbContent.class);
                return list;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        //创建example 设置查询的条件
        TbContentExample example = new TbContentExample();
        //执行查询
        example.createCriteria().andCategoryIdEqualTo(categoryId);

        List<TbContent> list = contentMapper.selectByExample(example);

        //添加（写入）缓存到redis数据库中
        try {
            System.out.println("没有缓存");
            jedisClient.hset(CONTENT_KEY, categoryId+"",JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }
}
