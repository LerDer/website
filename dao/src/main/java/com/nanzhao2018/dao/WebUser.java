package com.nanzhao2018.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ler
 */
@Getter
@Setter
@NoArgsConstructor
public class WebUser extends BaseDomain {
	private String userName;
	private String password;
	private String mail;
	private String phoneNumber;
	private String headImg;
	private String description;
	
	public WebUser(String userName , String mail , String phoneNumber) {
		this.userName = userName;
		this.mail = mail;
		this.phoneNumber = phoneNumber;
	}
}
