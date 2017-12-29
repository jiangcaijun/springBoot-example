package com.backstage.zeus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
public class ZeusApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZeusApiApplication.class, args);
	}
}