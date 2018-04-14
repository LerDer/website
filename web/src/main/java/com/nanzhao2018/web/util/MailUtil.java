package com.nanzhao2018.web.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * @author Ler
 * 发送邮件
 */
@Component
public class MailUtil {
	
	@Resource
	private JavaMailSender mailSender;
	
	/**
	 * 读取配置文件中的参数
	 */
	@Value("${spring.mail.username}")
	private String sender;
	
	/**
	 * 发送Html邮件
	 * @param mail  接收人
	 * @param title 标题
	 * @param text  文本
	 */
	public void sentMsg(String mail,String title,String text){
		MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(sender);
			//接收人
			helper.setTo(mail);
			//标题
			helper.setSubject(title);
			//文本
			helper.setText(text, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mailSender.send(message);
	}
}
