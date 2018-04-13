package com.nanzhao2018.service;

import com.nanzhao2018.dao.WebUser;
import com.nanzhao2018.util.PageResult;
import com.nanzhao2018.util.QueryObject;

/**
 * @author Ler
 */
public interface WebUserService {
	
	/**
	 * 新增用户
	 *
	 * @param webUser 用户
	 */
	void addUser(WebUser webUser);
	
	/**
	 * 逻辑删除用户
	 *
	 * @param id id
	 */
	void deleteUser(Long id);
	
	/**
	 * 更新用户
	 *
	 * @param webUser 用户
	 */
	void updateUser(WebUser webUser);
	
	/**
	 * 查询用户
	 *
	 * @param webUser 封装id或名字，用id查询结果只有一个
	 * @return list
	 */
	WebUser getUser(WebUser webUser);
	
	/**
	 * 高级分页查询
	 *
	 * @param queryObject 查询条件封装对象
	 * @return 分页查询结果
	 */
	PageResult listUser(QueryObject queryObject);
	
	void login(WebUser webUser);
}
