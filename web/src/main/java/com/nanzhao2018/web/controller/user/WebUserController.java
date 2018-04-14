package com.nanzhao2018.web.controller.user;

import com.nanzhao2018.dao.WebUser;
import com.nanzhao2018.service.WebUserService;
import com.nanzhao2018.web.util.ApiResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ler
 */
@Controller
@ResponseBody
public class WebUserController {
	
	private static final String RES_1 = "登录成功！";
	private static final String RES_2 = "用户名已存在！";
	private static final String RES_3 = "邮箱已使用！";
	private static final String RES_4 = "手机号已使用！";
	private static final String RES_5 = "注册成功！";
	private static final String RES_7 = "用户名可用！";
	private static final String RES_8 = "邮箱可用！";
	private static final String RES_9 = "手机号可用！";
	
	@Resource
	private WebUserService webUserService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ApiResult login(WebUser webUser) {
		try {
			webUserService.login(webUser);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(e.getMessage() , true);
		}
		return new ApiResult(RES_1 , false);
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ApiResult changePassword(String mail , String phoneNumber) {
		WebUser webUser = new WebUser();
		if (!StringUtils.isEmpty(mail)) {
			webUser.setMail(mail);
		}
		if (!StringUtils.isEmpty(phoneNumber)) {
			webUser.setPhoneNumber(phoneNumber);
		}
		List<WebUser> users = webUserService.getUser(webUser);
		if (users != null && users.size() == 1) {
			mail = users.get(0).getMail();
			phoneNumber = users.get(0).getPhoneNumber();
			//发送邮件或者短信
			
			
		}
		return null;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ApiResult register(WebUser webUser) {
		try {
			List<WebUser> user = webUserService.getUser(new WebUser(webUser.getUserName() , null , null));
			Assert.isTrue(user == null || user.size() == 0 , RES_2);
			user = webUserService.getUser(new WebUser(null , webUser.getMail() , null));
			Assert.isTrue(user == null || user.size() == 0 , RES_3);
			user = webUserService.getUser(new WebUser(null , null , webUser.getPhoneNumber()));
			Assert.isTrue(user == null || user.size() == 0 , RES_4);
			webUserService.addUser(webUser);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(e.getMessage() , true);
		}
		return new ApiResult(RES_5 , false);
	}
	
	/**
	 * 用户名重复检查
	 *
	 * @param userName 用户名
	 * @return 结果
	 */
	@RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
	public ApiResult checkNameRepeat(String userName) {
		try {
			List<WebUser> user = webUserService.getUser(new WebUser(userName , null , null));
			Assert.isTrue(user == null || user.size() == 0 , RES_2);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(e.getMessage() , true);
		}
		return new ApiResult(RES_7 , false);
	}
	
	/**
	 * 邮箱重复检查
	 *
	 * @param mail 邮箱
	 * @return 结果
	 */
	@RequestMapping(value = "/checkMail", method = RequestMethod.POST)
	public ApiResult checkMailRepeat(String mail) {
		try {
			List<WebUser> user = webUserService.getUser(new WebUser(null , mail , null));
			Assert.isTrue(user == null || user.size() == 0 , RES_3);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(e.getMessage() , true);
		}
		return new ApiResult(RES_8 , false);
	}
	
	/**
	 * 手机号重复检查
	 *
	 * @param phoneNumber 手机号
	 * @return 结果
	 */
	@RequestMapping(value = "/checkPhone", method = RequestMethod.POST)
	public ApiResult checkPhoneRepeat(String phoneNumber) {
		try {
			List<WebUser> user = webUserService.getUser(new WebUser(null , null , phoneNumber));
			Assert.isTrue(user == null || user.size() == 0 , RES_4);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(e.getMessage() , true);
		}
		return new ApiResult(RES_9 , false);
	}
}
