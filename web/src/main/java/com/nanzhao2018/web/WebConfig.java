package com.nanzhao2018.web;

import com.nanzhao2018.DaoConfig;
import com.nanzhao2018.ServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Ler
 */
@SpringBootApplication
@Import({ServiceConfig.class, DaoConfig.class})
@EnableAsync
@EnableScheduling
@EnableWebMvc
public class WebConfig {
	public static void main(String[] args) {
		SpringApplication.run(WebConfig.class , args);
	}
}
