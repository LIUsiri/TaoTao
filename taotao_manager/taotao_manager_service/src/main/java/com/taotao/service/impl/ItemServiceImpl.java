package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;
import java.util.List;

/**
 * class_name: ItemServiceImpl
 * package: com.taotao.service.impl
 * describe: TODO
 *
 * @author: Liuxianglong
 * @date: 2018/1/23
 * creat_time: 20:21
 **/

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource(name = "topicDestination")
    private Destination destination;

    @Override
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {

        PageHelper.startPage(page, rows);

        List<TbItem> tbItems = tbItemMapper.selectByExample(null);
        //构建分页对象,里面包含了总记录数
        PageInfo<TbItem> info = new PageInfo(tbItems);
        long total = info.getTotal();

        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(tbItems);
        result.setTotal(total);
        return result;
    }

    /**
     * 保存商品信息和商品描述
     *
     * @param tbItem
     * @param desc
     * @return
     */
    @Override
    public TaotaoResult saveItemAndItemDesc(TbItem tbItem, String desc) {
        try {
            long itemId = IDUtils.genItemId();
            tbItem.setId(itemId);
            tbItem.setStatus((byte) 1);
            tbItem.setCreated(new Date());
            tbItem.setUpdated(tbItem.getCreated());

            tbItemMapper.insertSelective(tbItem);
            //插入商品描述
            TbItemDesc tbItemDesc = new TbItemDesc();
            tbItemDesc.setItemDesc(desc);
            tbItemDesc.setCreated(tbItem.getCreated());
            tbItemDesc.setUpdated(tbItem.getCreated());
            tbItemDesc.setItemId(itemId);
            tbItemDescMapper.insertSelective(tbItemDesc);


            //在添加商品的时候发送消息   --->发送id
            jmsTemplate.send(destination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(itemId + "");


                }
            });

        } catch (Exception e) {
            e.printStackTrace();

        }

        return TaotaoResult.ok();
    }

    @Override
    public TbItem getTbItemById(Long id) {

        TbItem item = tbItemMapper.selectByPrimaryKey(id);

        return item;
    }

    @Override
    public void updateTbItem(TbItem item) {

        tbItemMapper.updateByPrimaryKeySelective(item);
    }

    @Override
    public void deleteById(Long id) {
        tbItemMapper.deleteByPrimaryKey(id);
    }
}
