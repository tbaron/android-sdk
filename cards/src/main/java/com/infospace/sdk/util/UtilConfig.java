package com.infospace.sdk.util;

import com.infospace.sdk.Container;

public final class UtilConfig {
	private UtilConfig() {
	}

	public static void register() {
		Container.register(new AndroidLog());
	}
}
