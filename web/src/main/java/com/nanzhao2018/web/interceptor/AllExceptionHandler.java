package com.nanzhao2018.web.interceptor;

import com.nanzhao2018.web.util.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Ler
 * @date 2018-07-03 22:12
 */
@ControllerAdvice
public class AllExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(AllExceptionHandler.class);
	
	/**
	 * 处理所有不可知的异常
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ApiResult handleException(HttpServletRequest request , IllegalArgumentException e) {
		String url = request.getRequestURI();
		log.error(url + "\n Exception: " , e);
		return new ApiResult(e.getMessage() , true);
	}
	
	/**
	 * 处理所有业务校验异常
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ApiResult handleBusinessException(HttpServletRequest request , IllegalArgumentException e) {
		String url = request.getRequestURI();
		log.error(url + "\n IllegalArgumentException: " , e);
		return new ApiResult(e.getMessage() , true);
	}
}