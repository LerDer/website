package com.nanzhao2018.web.controller.user;

import com.nanzhao2018.dao.WebUser;
import com.nanzhao2018.service.WebUserService;
import com.nanzhao2018.web.util.ApiResult;
import com.nanzhao2018.web.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

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
	private static final String RES_10 = "请输入正确的邮箱！";
	private static final String RES_11 = "请输入正确的手机号！";
	private static final String RES_12 = "用户名只能是中文或英文，长度20！";
	private static final String RES_13 = "请输入正确的邮箱或手机号！";
	private static final String RES_14 = "重置密码的链接已发送到绑定的邮箱，只能点击一次且30分钟内有效！";
	private static final String RES_15 = "重置密码链接已失效，请重新申请！";
	private static final String RES_16 = "请重置密码！";
	private static final String RES_17 = "请输入密码！";
	private static final String RES_18 = "查询用户失败！";
	private static final String RES_19 = "密码重置成功！";
	
	private static final String CHANGE_PASSWORD_KEY = "changePasswordAuthKey";
	private static final String CAN_CHANGE_PASSWORD = "CanChangePassword";
	
	@Resource
	private WebUserService webUserService;
	
	/**
	 * 登录
	 *
	 * @param webUser 用户
	 * @return 结果
	 */
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
	
	/**
	 * 密码重置
	 *
	 * @param mail        邮箱
	 * @param phoneNumber 手机号
	 * @return 结果
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ApiResult applyResetPassword(String mail , String phoneNumber) {
		try {
			WebUser webUser = new WebUser();
			if (!StringUtils.isEmpty(mail)) {
				webUser.setMail(mail);
			}
			if (!StringUtils.isEmpty(phoneNumber)) {
				webUser.setPhoneNumber(phoneNumber);
			}
			List<WebUser> users = webUserService.getUser(webUser);
			Assert.isTrue(users != null && users.size() == 1 , RES_13);
			//生成修改密码的密钥
			String authKey = UUID.randomUUID().toString().replace("-" , "");
			SessionUtil.session().setAttribute(CHANGE_PASSWORD_KEY , authKey);
			//todo:发送邮件
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(e.getMessage() , true);
		}
		return new ApiResult(RES_14 , false);
	}
	
	/**
	 * 跳转到修改密码页面之前检查密钥
	 *
	 * @param authKey 密钥
	 * @return 结果
	 */
	@RequestMapping(value = "/preChangePassword", method = RequestMethod.POST)
	public ApiResult preChangePassword(String authKey , String name) {
		try {
			Object attribute = SessionUtil.session().getAttribute(CHANGE_PASSWORD_KEY);
			Assert.isTrue(attribute != null && !StringUtils.isEmpty(authKey) && authKey.equals(attribute.toString()) ,
					RES_15);
			//删除Session中的值，只能调一次
			SessionUtil.session().removeAttribute(CHANGE_PASSWORD_KEY);
			SessionUtil.session().setAttribute(CAN_CHANGE_PASSWORD , name);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(e.getMessage() , true);
		}
		return new ApiResult(RES_16 , false);
	}
	
	/**
	 * 重置密码接口
	 *
	 * @param user 用户
	 * @return 结果
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ApiResult changePassword(WebUser user) {
		try {
			String name = (String) SessionUtil.session().getAttribute(CAN_CHANGE_PASSWORD);
			SessionUtil.session().removeAttribute(CAN_CHANGE_PASSWORD);
			Assert.isTrue(user.getPassword() != null , RES_17);
			List<WebUser> userList = webUserService.getUser(new WebUser(name , null , null));
			Assert.isTrue(userList != null && userList.size() == 1 , RES_18);
			WebUser webUser = userList.get(0);
			webUser.setPassword(user.getPassword());
			webUserService.updateUser(webUser);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(e.getMessage() , true);
		}
		return new ApiResult(RES_19 , false);
	}
	
	/**
	 * 注册
	 *
	 * @param webUser 用户
	 * @return 结果
	 */
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
			String pattern = "^[\\u4e00-\\u9fa5|\\w]{0,20}$";
			Assert.isTrue(userName.matches(pattern) , RES_12);
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
			String pattern = "^\\w+([-+.]\\w+)@\\w+([-.]\\w+).\\w+([-.]\\w+)*$";
			Assert.isTrue(mail.matches(pattern) , RES_10);
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
			String pattern = "^1[3|4|5|7|8][0-9]{9}$";
			Assert.isTrue(phoneNumber.matches(pattern) , RES_11);
			List<WebUser> user = webUserService.getUser(new WebUser(null , null , phoneNumber));
			Assert.isTrue(user == null || user.size() == 0 , RES_4);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(e.getMessage() , true);
		}
		return new ApiResult(RES_9 , false);
	}
}
