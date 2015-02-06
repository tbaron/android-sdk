package com.infospace.sdk.cards.binders;

import android.text.Html;
import android.view.*;
import android.widget.TextView;

import com.infospace.sdk.cards.R;
import com.infospace.sdk.models.*;

import java.util.List;

public abstract class StandardCardBinder<TResult extends StandardResult>
	extends CardBinder<TResult> {

	@Override
	protected void onBind() {
		super.onBind();

		TResult model = getModel();

		setupTitle(model.getTitle());
		setupDescription(model.getDescription());
		setupUrl(model.getDisplayURL());
		setupDeeplinks(model.getDeeplinks());
	}

	private void setupTitle(String title) {
		TextView textView = findTextView(R.id.insp_card_title);

		if (textView != null) {
			if (title == null) {
				title = "";
			}

			textView.setText(Html.fromHtml(title));
		}
	}

	private void setupDescription(String description) {
		TextView textView = findTextView(R.id.insp_card_description);

		if (textView != null) {
			if (description == null) {
				description = "";
			}

			textView.setText(Html.fromHtml(description));
		}
	}

	private void setupUrl(String displayUrl) {
		TextView textView = findTextView(R.id.insp_card_url);

		if (textView != null) {
			if (displayUrl == null) {
				displayUrl = "";
			}

			textView.setText(Html.fromHtml(displayUrl));
		}
	}

	private void setupDeeplinks(List<DeeplinkResult> deeplinks) {
		ViewGroup container = findViewGroup(R.id.insp_card_deeplinks);

		if (container == null) {
			return;
		}

		container.removeAllViews();

		if (deeplinks == null || deeplinks.isEmpty()) {
			container.setVisibility(View.GONE);
			return;
		}

		container.setVisibility(View.VISIBLE);

		for (DeeplinkResult result : deeplinks) {
			container.addView(getDeeplinkView(result, container));
		}
	}

	private View getDeeplinkView(ResultNode result, ViewGroup container) {
		// TODO: can we reuse existing child views?
		View view = getViewFactory().getView(result, null, container);

		getBinderFactory().bind(result, view);

		return view;
	}
}
