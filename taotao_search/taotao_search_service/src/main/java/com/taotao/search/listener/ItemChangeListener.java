package com.taotao.search.listener;


import com.taotao.common.pojo.SearchItem;
import com.taotao.search.mapper.SearchItemMapper;
import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.List;

/**
 * class_name: ItemChangeListener
 * package: com.taotao.search.listener
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/2/2
 * creat_time: 21:42
 **/


public class ItemChangeListener implements MessageListener {


    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private SearchItemService searchItemService;

    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            try {
                String itemIdStr = ((TextMessage) message).getText();
                Long itemId= Long.valueOf(itemIdStr);
                SearchItem item = searchItemMapper.getSearchItemById(itemId);

                searchItemService.updateIndex(item);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
