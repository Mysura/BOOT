package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class SampleDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SampleDemoApplication.class, args);
	}
	@Bean
	public CacheManager cacheManager(){
		GuavaCacheManager cacheManager = new GuavaCacheManager("employee");
		return cacheManager;
	}
}
