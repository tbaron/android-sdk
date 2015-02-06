package com.infospace.sdk.models.actions;

import android.content.Intent;
import android.net.Uri;

class EmailIntentAction
	extends
	IntentOnActionListener {
	private final String emailAddress;

	protected EmailIntentAction(AndroidActivityStarter starter, int layoutId, String emailAddress) {
		super(starter, layoutId);
		this.emailAddress = emailAddress;
	}

	@Override
	protected Intent createIntent() {
		Intent intent = new Intent(Intent.ACTION_SENDTO);
		intent.setData(Uri.parse("mailto:" + emailAddress));
		return Intent.createChooser(intent, null);
	}

	protected String getEmail() {
		return emailAddress;
	}
}
