package com.nanzhao2018.domain;

import com.nanzhao2018.dao.WebUser;
import com.nanzhao2018.util.QueryObject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ler
 */
@Repository
public interface WebUserMapper {
	
	/**
	 * 新增用户
	 *
	 * @param webUser 用户
	 * @return 影响的行数
	 */
	int insertUser(WebUser webUser);
	
	/**
	 * 逻辑删除
	 *
	 * @param id id
	 * @return 影响的行数
	 */
	int deletedUserById(Long id);
	
	/**
	 * 更新用户
	 *
	 * @param webUser 用户封装对象
	 * @return 影响行数
	 */
	int updateWebUser(WebUser webUser);
	
	/**
	 * 使用Id查询用户
	 *
	 * @param webUser 把id或名字封装到用户对象中
	 * @return 用户
	 */
	WebUser getUserByIdOrName(WebUser webUser);
	
	/**
	 * 查询数量
	 *
	 * @param queryObject 查询条件封装对象
	 * @return 数量
	 */
	int listCount(QueryObject queryObject);
	
	/**
	 * 高级分页查询
	 * @param queryObject 查询条件
	 * @return  结果
	 */
	List<WebUser> listWebUser(QueryObject queryObject);
	
}
