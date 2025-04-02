package com.salih.restbucks.common.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum StaticLogger {
	;

	public static Logger logger(Class<?> clazz) {
		return LogManager.getLogger(clazz);
	}

	public static void logEnter(Class<?> clazz) {
		Logger logger = LogManager.getLogger(clazz.getName());
		if (logger.isTraceEnabled()) {
			logger.trace("➡\uFE0F Entering: {}.{}", clazz.getSimpleName(), getCallerMethodName());
		}
	}

	public static void logExit(Class<?> clazz) {
		Logger logger = LogManager.getLogger(clazz.getName());
		if (logger.isTraceEnabled()) {
			logger.trace("⬅\uFE0F Exiting: {}.{}", clazz.getSimpleName(), getCallerMethodName());
		}
	}

	private static String getCallerMethodName() {
		return Thread.currentThread().getStackTrace()[3].getMethodName();
		// [0] = getStackTrace
		// [1] = getCallerMethodName
		// [2] = logEnter or logExit
		// [3] = ✨ actual calling method
	}
}
