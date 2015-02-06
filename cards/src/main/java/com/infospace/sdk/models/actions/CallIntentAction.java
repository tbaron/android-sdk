package com.infospace.sdk.models.actions;

import android.content.Intent;
import android.net.Uri;

class CallIntentAction
	extends
	IntentOnActionListener {
	private String phoneNumber;

	protected CallIntentAction(AndroidActivityStarter starter, int layoutId, String phoneNumber) {
		super(starter, layoutId);
		this.phoneNumber = phoneNumber;
	}

	@Override
	protected Intent createIntent() {
		Intent intent = new Intent(Intent.ACTION_DIAL);
		intent.setData(Uri.parse("tel:" + phoneNumber));
		return intent;
	}

	protected String getPhoneNumber() {
		return phoneNumber;
	}
}
