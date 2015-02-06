package com.infospace.sdk.cards.demo.data;

import android.text.Html;
import android.widget.TextView;

import com.infospace.sdk.cards.ViewBinder;

public class DemoHeaderViewBinder extends ViewBinder<DemoHeaderResult> {
	@Override
	protected void onBind() {
		setupTitle(getModel().getTitle());
		setupDescription(getModel().getDescription());
	}

	private void setupTitle(String title) {
		TextView textView = findTextView(com.infospace.sdk.cards.R.id.insp_card_title);

		if (textView != null) {
			if (title == null) {
				title = "";
			}

			textView.setText(Html.fromHtml(title));
		}
	}

	private void setupDescription(String description) {
		TextView textView = findTextView(com.infospace.sdk.cards.R.id.insp_card_description);

		if (textView != null) {
			if (description == null) {
				description = "";
			}

			textView.setText(Html.fromHtml(description));
		}
	}
}
