package com.nanzhao2018.web.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ler
 */
@Getter
@Setter
public class ApiResult {
	private String context;
	private Boolean hasErrors;
	
	public ApiResult(String context , Boolean hasErrors) {
		this.context = context;
		this.hasErrors = hasErrors;
	}
}
