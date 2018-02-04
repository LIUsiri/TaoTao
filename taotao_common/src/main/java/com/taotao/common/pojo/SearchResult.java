package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;
/**
 * 商品搜索的分页信息结果对象
 * @title SearchResult.java
 * <p>description</p>
 * <p>company: www.itheima.com</p>
 * @author ljh 
 * @version 1.0
 */
public class SearchResult implements Serializable {
	private List<SearchItem> itemList;// 搜索结果列表
	private long recordCount;// 总记录数
	private long pageCount;// 总页数
	private Integer page;
	private Integer rows;

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {

		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public List<SearchItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

}
