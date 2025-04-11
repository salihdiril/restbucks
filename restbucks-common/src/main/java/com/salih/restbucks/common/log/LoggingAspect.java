package com.salih.restbucks.common.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.salih.restbucks.common.log.helpers.Emoji;

import jakarta.annotation.PostConstruct;

@Aspect
@Component
public class LoggingAspect implements Loggable {
	private static final Logger apiLogger = LogManager.getLogger("com.salih.restbucks.common.log.LoggingAspect.api");

	@PostConstruct
	public void init() {
		logEnter();
		logger().info("LoggingAspect initialized {}", Emoji.CHECK);
		logExit();
	}

	@Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
	public static void inRestControllers() {
	}

	@Before("inRestControllers()")
	public void logApiRequests(JoinPoint joinPoint) {
		final Signature signature = joinPoint.getSignature();
		final String clazz = signature.getDeclaringType().getSimpleName();
		final String method = signature.getName();
		apiLogger.info("{} Entering: {}.{} with args {}", Emoji.ENTERING, clazz, method, joinPoint.getArgs());
	}
}
