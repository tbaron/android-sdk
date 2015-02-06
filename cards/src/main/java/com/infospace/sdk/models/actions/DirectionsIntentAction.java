package com.infospace.sdk.models.actions;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

class DirectionsIntentAction
	extends
	IntentOnActionListener {
	private final String address;
	private final String title;

	protected DirectionsIntentAction(AndroidActivityStarter starter, int layoutId, String address, String title) {
		super(starter, layoutId);
		this.address = address;
		this.title = title;
	}

	@Override
	protected Intent createIntent() {
		StringBuilder uri = new StringBuilder("geo:0,0?q=")
			.append(Uri.encode(address));

		if (!TextUtils.isEmpty(title)) {
			uri.append(" (")
				.append(Uri.encode(title))
				.append(")");
		}

		return new Intent(Intent.ACTION_VIEW, Uri.parse(uri.toString()));
	}
}
