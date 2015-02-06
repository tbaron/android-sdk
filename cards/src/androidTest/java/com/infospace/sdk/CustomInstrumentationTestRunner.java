package com.infospace.sdk;

import android.os.Bundle;
import android.test.InstrumentationTestRunner;

public class CustomInstrumentationTestRunner extends InstrumentationTestRunner {

	@Override
	public void onCreate(final Bundle arguments) {
		super.onCreate(arguments);

		// temporary workaround for an incompatibility in current dexmaker (1.1) implementation and Android >= 4.3
		// cf. https://code.google.com/p/dexmaker/issues/detail?id=2 for details
		System.setProperty("dexmaker.dexcache", getTargetContext().getCacheDir().toString());
	}
}
