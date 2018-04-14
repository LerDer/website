package com.nanzhao2018.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Ler
 */
@Getter
@Setter
public class BaseDomain {
	protected Long id;
	protected Date createTime;
	protected Date modifyTime;
	protected char isDeleted = 'n';
}
