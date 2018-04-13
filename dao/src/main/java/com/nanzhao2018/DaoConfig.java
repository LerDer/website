package com.nanzhao2018;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Ler
 */
@SpringBootApplication
@MapperScan("com.nanzhao2018.domain")
public class DaoConfig {
	public static void main(String[] args) {
		SpringApplication.run(DaoConfig.class , args);
	}
}

