package com.nanzhao2018.util;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 查询工具
 *
 * @author Ler
 */
@Getter
@Setter
public class QueryObject {
	
	private String keyword;
	private Date startDate;
	private Date endDate;
	private int currentPage;
	private int pageSize;
	
	public int getStart() {
		return (currentPage - 1) * pageSize;
	}
}
