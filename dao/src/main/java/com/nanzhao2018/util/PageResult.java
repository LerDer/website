package com.nanzhao2018.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具
 * @author Ler
 */
public class PageResult {
	/**
	 * 首页
	 */
	private final Integer beginPage = 1;
	/**
	 * 查询的结果集
	 */
	private List<?> listData;
	/**
	 * 结果总数
	 */
	private Integer totalCount;
	/**
	 * 一页里面最多显示的结果条数
	 */
	private Integer pageSize;
	/**
	 * 当前页
	 */
	private Integer currentPage;
	/**
	 * 总页数
	 */
	private Integer totalPage;
	/**
	 * 上一页
	 */
	private Integer prevPage;
	/**
	 * 下一页
	 */
	private Integer nextPage;
	
	public PageResult(Integer currentPage , Integer pageSize , List<?> listData , Integer totalCount) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.listData = listData;
		this.totalCount = totalCount;
		this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		this.prevPage = currentPage - 1 > 0 ? currentPage - 1 : currentPage;
		this.nextPage = currentPage + 1 < totalPage ? currentPage + 1 : totalPage;
	}
	
	public static PageResult empty(Integer pageSize) {
		return new PageResult(1 , pageSize , new ArrayList<>() , 0);
	}
	
	public Integer getTotalPage() {
		return totalPage == 0 ? 1 : totalPage;
	}
}
