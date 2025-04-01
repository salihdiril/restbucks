package com.salih.restbucks.server.common.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Loggable {
	default Logger logger() {
		return LogManager.getLogger(this.getClass().getName());
	}

	default void logEnter() {
		if (logger().isTraceEnabled()) {
			logger().trace("➡️ Entering: {}.{}", getClass().getSimpleName(), getCallerMethodName());
		}
	}

	default void logExit() {
		if (logger().isTraceEnabled()) {
			logger().trace("⬅️ Exiting: {}.{}", getClass().getSimpleName(), getCallerMethodName());
		}
	}

	private String getCallerMethodName() {
		return Thread.currentThread().getStackTrace()[3].getMethodName();
		// [0] = getStackTrace
		// [1] = getCallerMethodName
		// [2] = logEnter or logExit
		// [3] = ✨ actual calling method
	}
}
