package com.nanzhao2018.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ler
 */
@Controller
public class Hello {
	
	@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
	@ResponseBody
	public Object hello(@PathVariable("name") String name , HttpServletResponse response) {
		Map<String, Object> hello = new HashMap<>(16);
		hello.put("name" , name);
		response.setStatus(HttpServletResponse.SC_OK);
		return hello;
	}
}
