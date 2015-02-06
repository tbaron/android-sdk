package com.infospace.sdk.cards.binders;

import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.github.kevinsawicki.timeago.TimeAgo;
import com.infospace.sdk.cards.R;
import com.infospace.sdk.models.NewsResult;

import java.util.*;

public class NewsCardBinder extends StandardCardBinder<NewsResult> {
	@Override
	protected void onBind() {
		super.onBind();

		setupSource(getModel().getSource());
		setupDate(getModel().getDate());
		setupThumbnail(getModel().getThumbnailUrl());
	}

	private void setupSource(String source) {
		TextView view = findTextView(R.id.insp_card_source);

		if (view != null) {
			view.setText(source);
		}
	}

	private void setupDate(Date date) {
		TextView view = findTextView(R.id.insp_card_date);

		if (view != null) {
			String str = date == null
				? ""
				: new TimeAgo().timeAgo(date);

			view.setText(String.format(Locale.getDefault(), str, date));
		}
	}

	private void setupThumbnail(String url) {
		ImageView view = findImageView(R.id.insp_card_image);

		if (view == null) {
			return;
		}

		if (TextUtils.isEmpty(url)) {
			view.setVisibility(View.GONE);
			return;
		}

		view.setVisibility(View.VISIBLE);

		getPicasso()
			.load(url)
			.fit()
			.centerCrop()
			.into(view);
	}
}
