package com.infospace.sdk.cards.binders;

import android.text.Html;
import android.widget.TextView;

import com.infospace.sdk.cards.R;
import com.infospace.sdk.models.RelatedResult;

public class RelatedCardBinder
	extends CardBinder<RelatedResult> {

	@Override
	protected void onBind() {
		super.onBind();

		setupTitle();
	}

	private void setupTitle() {
		TextView textView = findTextView(R.id.insp_card_title);

		if (textView != null) {
			textView.setText(Html.fromHtml(getModel().getTitle()));
		}
	}
}
