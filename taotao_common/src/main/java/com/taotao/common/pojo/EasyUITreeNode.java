package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * class_name: EasyUITreeNode
 * package: com.taotao.common.pojo
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/24
 * creat_time: 15:51
 **/


public class EasyUITreeNode  implements Serializable{
    private long id;
    private String text;
    private String state;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
