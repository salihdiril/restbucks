package com.salih.restbucks.common.log.helpers;

public enum Emoji {
	ENTERING("\u27A1"),       // ➡ Entering a method or process
	EXITING("\u2B05"),        // ⬅ Exiting a method or process
	CHECK("\u2705"),          // ✅ Success, passed check, or valid input
	ERROR("\u274C"),          // ❌ Error, failure, or rejected operation
	ROCKET("\uD83D\uDE80"),   // 🚀 Application startup or launching
	WORLD("\uD83C\uDF10"),    // 🌐 Network or API-related activity
	CART("\uD83D\uDED2");     // 🛒 Order or cart-related action

	private final String symbol;

	Emoji(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return symbol;
	}
}
