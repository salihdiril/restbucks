package com.salih.restbucks.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.salih.restbucks.common.log.StaticLogger;

@SpringBootApplication
@ComponentScan(basePackages = "com.salih.restbucks")
public class RestbucksServerApi {
	public static void main(String[] args) {
		StaticLogger.logger(RestbucksServerApi.class).atInfo().log("🚀 Starting Restbucks server...");
		SpringApplication.run(RestbucksServerApi.class, args);
	}
}