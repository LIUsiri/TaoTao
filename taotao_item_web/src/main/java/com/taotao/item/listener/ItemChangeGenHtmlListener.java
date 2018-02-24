package com.taotao.item.listener;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemCatService;
import com.taotao.service.ItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import sun.security.krb5.internal.crypto.Des;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * class_name: ItemChangeGenHtmlListener
 * package: com.taotao.item.listener
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/2/5
 * creat_time: 21:34
 **/


public class ItemChangeGenHtmlListener implements MessageListener {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCatService itemCatService;

    @Override
    public void onMessage(Message message) {
        //当获取到消息的时候执行
        if(message instanceof TextMessage){
            try {
                //1.获取到消息的内容,也就是id
                String itemIdstr = ((TextMessage) message).getText();
                if(StringUtils.isNotBlank(itemIdstr)) {

                    Long itemId = Long.valueOf(itemIdstr);
                    //2.通过商品的id获取商品的数据
                    TbItem tbItem = itemService.getTbItemById(itemId);
                    //这是一个有图片的pojo
                    Item item = new Item(tbItem);

                    TbItemDesc itemDesc = itemCatService.getItemDesc(itemId);
                    //3.通过freemark的方法生成html页面
                    genHTML("item.ftl", item, itemDesc);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    //根据模板生成静态页面
    private void genHTML(String templateName, TbItem item, TbItemDesc itemDesc) throws Exception {
        //1.创建config对象
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        //2.设置模板文件所在的目录和设置模板文件的字符编码 spring已经帮我们做了
        //3.创建模板文件
        //4.获取模板对象
        Template template = configuration.getTemplate(templateName);
        Map<Object, Object> map = new HashMap<>();
        map.put("item",item);
        map.put("itemDesc",itemDesc);
        //创建流对象
        FileWriter fileWriter = new FileWriter(new File("F:\\CRM_image\\" + item.getId() + ".html"));
        template.process(map,fileWriter);
        fileWriter.close();

    }
}
