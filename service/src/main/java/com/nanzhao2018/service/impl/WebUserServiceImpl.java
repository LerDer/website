package com.nanzhao2018.service.impl;

import com.nanzhao2018.dao.WebUser;
import com.nanzhao2018.domain.WebUserMapper;
import com.nanzhao2018.service.WebUserService;
import com.nanzhao2018.util.PageResult;
import com.nanzhao2018.util.QueryObject;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ler
 */
@Service
public class WebUserServiceImpl implements WebUserService {
	
	private static final String ERR_1 = "新增失败！";
	private static final String ERR_2 = "删除失败！";
	private static final String ERR_3 = "更新失败！";
	private static final String ERR_4 = "缺少查询参数！";
	private static final String ERR_5 = "请输入用户名！";
	private static final String ERR_6 = "请输入密码！";
	private static final String ERR_7 = "用户名或密码错误，登录失败！";
	
	
	@Resource
	private WebUserMapper webUserMapper;
	
	@Override
	public void addUser(WebUser webUser) {
		int res = webUserMapper.insertUser(webUser);
		Assert.isTrue(res == 1 , ERR_1);
	}
	
	@Override
	public void deleteUser(Long id) {
		int res = webUserMapper.deletedUserById(id);
		Assert.isTrue(res == 1 , ERR_2);
	}
	
	@Override
	public void updateUser(WebUser webUser) {
		int res = webUserMapper.updateWebUser(webUser);
		Assert.isTrue(res == 1 , ERR_3);
	}
	
	@Override
	public WebUser getUser(WebUser webUser) {
		Assert.isTrue(webUser.getId() != null || webUser.getUserName() != null , ERR_4);
		return webUserMapper.getUserByIdOrName(webUser);
	}
	
	@Override
	public PageResult listUser(QueryObject queryObject) {
		int count = webUserMapper.listCount(queryObject);
		if (count <= 0) {
			return PageResult.empty(queryObject.getPageSize());
		} else {
			List<WebUser> webUsers = webUserMapper.listWebUser(queryObject);
			return new PageResult(queryObject.getCurrentPage() , queryObject.getPageSize() , webUsers , count);
		}
	}
	
	@Override
	public void login(WebUser webUser) {
		Assert.isTrue(!StringUtils.isEmpty(webUser.getUserName()) , ERR_5);
		Assert.isTrue(!StringUtils.isEmpty(webUser.getPassword()) , ERR_6);
		WebUser resUser = webUserMapper.getUserByIdOrName(webUser);
		Assert.isTrue(resUser != null , ERR_7);
	}
}
