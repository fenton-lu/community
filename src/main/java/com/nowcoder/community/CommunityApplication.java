package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CommunityApplication {

	@PostConstruct
	public void init() {
		// redis和elasticsearch底层都基于netty，两者启动时会有冲突，冲突主要体现在elasticsearch的代码中
		// 解决netty启动冲突问题
		// see Netty4Utils.setAvailableProcessors()方法
		System.setProperty("es.set.netty.runtime.available.processors", "false");
	}

	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

}
