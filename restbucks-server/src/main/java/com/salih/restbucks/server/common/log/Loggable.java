package com.salih.restbucks.server.common.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Loggable {
	default Logger logger() {
		return LogManager.getLogger(this.getClass().getName());
	}

	default void logEnter(String methodName, Object... args) {
		if (logger().isTraceEnabled()) {
			logger().trace("➡️ Entering: {} with args {}", methodName, args);
		}
	}

	default void logExit(String methodName) {
		if (logger().isTraceEnabled()) {
			logger().trace("⬅️ Exiting: {}", methodName);
		}
	}
}
