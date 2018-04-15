package com.nanzhao2018.service.impl;

import com.alibaba.fastjson.JSON;
import com.nanzhao2018.dao.WebUser;
import com.nanzhao2018.domain.WebUserMapper;
import com.nanzhao2018.service.WebUserService;
import com.nanzhao2018.util.PageResult;
import com.nanzhao2018.util.QueryObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ler
 */
@Service
public class WebUserServiceImpl implements WebUserService {
	
	private static final String ERR_1 = "注册失败！";
	private static final String ERR_2 = "删除失败！";
	private static final String ERR_3 = "更新失败！";
	private static final String ERR_4 = "缺少查询参数！";
	private static final String ERR_5 = "请输入用户名！";
	private static final String ERR_6 = "请输入密码！";
	private static final String ERR_7 = "用户名或密码错误，登录失败！";
	private static final String ERR_8 = "用户名已存在！";
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private WebUserMapper webUserMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addUser(WebUser user) {
		WebUser webUser = new WebUser();
		webUser.setUserName(user.getUserName());
		webUser.setPassword(user.getPassword());
		webUser.setMail(user.getMail());
		webUser.setPhoneNumber(user.getPhoneNumber());
		webUser.setHeadImg(user.getHeadImg());
		webUser.setDescription(user.getDescription());
		int res = webUserMapper.insertUser(webUser);
		Assert.isTrue(res == 1 , ERR_1);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteUser(Long id) {
		int res = webUserMapper.deletedUserById(id);
		Assert.isTrue(res == 1 , ERR_2);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateUser(WebUser user) {
		WebUser webUser = new WebUser();
		webUser.setUserName(user.getUserName());
		webUser.setPassword(user.getPassword());
		webUser.setMail(user.getMail());
		webUser.setPhoneNumber(user.getPhoneNumber());
		webUser.setHeadImg(user.getHeadImg());
		webUser.setDescription(user.getDescription());
		int res = webUserMapper.updateWebUser(webUser);
		Assert.isTrue(res == 1 , ERR_3);
	}
	
	@Override
	public List<WebUser> getUser(WebUser webUser) {
		Assert.isTrue(webUser.getId() != null || webUser.getUserName() != null ||
					webUser.getMail() != null || webUser.getPhoneNumber() != null, ERR_4);
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
	public void login(WebUser user) {
		Assert.isTrue(!StringUtils.isEmpty(user.getUserName()) , ERR_5);
		Assert.isTrue(!StringUtils.isEmpty(user.getPassword()) , ERR_6);
		WebUser webUser  =new WebUser();
		webUser.setUserName(user.getUserName());
		webUser.setPassword(user.getPassword());
		log.info("WebUser:{}", JSON.toJSONString(webUser));
		List<WebUser> resUsers = webUserMapper.getUserByIdOrName(webUser);
		Assert.isTrue(resUsers != null && resUsers.size() == 1 , ERR_7);
	}
}
