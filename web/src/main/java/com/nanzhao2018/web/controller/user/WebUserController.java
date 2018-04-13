package com.nanzhao2018.web.controller.user;

import com.nanzhao2018.dao.WebUser;
import com.nanzhao2018.service.WebUserService;
import com.nanzhao2018.web.util.ApiResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author Ler
 */
@Controller
public class WebUserController {
	
	private static final String RES_1 = "登录成功！";
	private static final String RES_3 = "！";
	@Resource
	private WebUserService webUserService;
	
	@RequestMapping(value = "/login", method = {RequestMethod.GET , RequestMethod.POST})
	@ResponseBody
	public ApiResult login(WebUser webUser) {
		try {
			webUserService.login(webUser);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(e.getMessage() , true);
		}
		return new ApiResult(RES_1 , false);
	}
}
