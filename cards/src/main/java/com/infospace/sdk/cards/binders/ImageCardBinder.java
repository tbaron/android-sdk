package com.infospace.sdk.cards.binders;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.infospace.sdk.cards.R;
import com.infospace.sdk.models.ImageResult;

public class ImageCardBinder extends StandardCardBinder<ImageResult> {
	@Override
	protected void onBind() {
		super.onBind();
		setupThumbnail(getModel().getThumbnailUrl());
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
