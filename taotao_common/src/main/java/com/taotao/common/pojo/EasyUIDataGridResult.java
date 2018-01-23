package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * class_name: EasyUIDataGridResult
 * package: com.taotao.common.pojo
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/23
 * creat_time: 19:51
 **/

public class EasyUIDataGridResult implements Serializable{
    //总记录数
    private Long total;
    //对象的集合
    private List rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "EasyUIDataGridResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
