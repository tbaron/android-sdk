package com.infospace.sdk.models.actions;

import android.content.Intent;

abstract class IntentOnActionListener
	implements
	OnActionListener {
	final IntentStarter starter;
	private final int layoutId;

	protected IntentOnActionListener(IntentStarter starter, int layoutId) {
		if (starter == null) {
			throw new IllegalArgumentException();
		}

		this.starter = starter;
		this.layoutId = layoutId;
	}

	@Override
	public int getLayoutId() {
		return this.layoutId;
	}

	@Override
	public void onAction() {
		starter.startIntent(createIntent());
	}

	protected abstract Intent createIntent();
}
