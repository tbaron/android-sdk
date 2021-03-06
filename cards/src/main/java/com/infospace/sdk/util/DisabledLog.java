package com.infospace.sdk.util;

public class DisabledLog implements ILog {
	@Override
	public int v(String tag, String msg) {
		return 0;
	}

	@Override
	public int v(String tag, String msg, Throwable tr) {
		return 0;
	}

	@Override
	public int d(String tag, String msg) {
		return 0;
	}

	@Override
	public int d(String tag, String msg, Throwable tr) {
		return 0;
	}

	@Override
	public int i(String tag, String msg) {
		return 0;
	}

	@Override
	public int i(String tag, String msg, Throwable tr) {
		return 0;
	}

	@Override
	public int w(String tag, String msg) {
		return 0;
	}

	@Override
	public int w(String tag, String msg, Throwable tr) {
		return 0;
	}

	@Override
	public int w(String tag, Throwable tr) {
		return 0;
	}

	@Override
	public int e(String tag, String msg) {
		return 0;
	}

	@Override
	public int e(String tag, String msg, Throwable tr) {
		return 0;
	}

	@Override
	public int wtf(String tag, String msg) {
		return 0;
	}

	@Override
	public int wtf(String tag, Throwable tr) {
		return 0;
	}

	@Override
	public int wtf(String tag, String msg, Throwable tr) {
		return 0;
	}

	@Override
	public String getStackTraceString(Throwable tr) {
		return null;
	}

	@Override
	public int println(int priority, String tag, String msg) {
		return 0;
	}
}
