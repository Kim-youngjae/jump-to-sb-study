package com.mysite.sbb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication // 다음 애너테이션을 통해 스프링부트의 모든 설정이 관리되어진다.
@EnableJpaAuditing
public class SbbApplication {
	public static void main(String[] args) {
		SpringApplication.run(SbbApplication.class, args);
	}
}
