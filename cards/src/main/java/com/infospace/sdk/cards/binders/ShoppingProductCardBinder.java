package com.infospace.sdk.cards.binders;

import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.infospace.sdk.cards.R;
import com.infospace.sdk.models.*;

import java.util.List;

public class ShoppingProductCardBinder
	extends StandardCardBinder<ShoppingProductResult> {

	@Override
	protected void onBind() {
		super.onBind();

		setupImage();
		setupOnSale(getModel().isOnSale());
		setupPrice(getModel().getPrice());
		setupPromo();
		setupRating(getModel().getRating());
	}

	private void setupImage() {
		ImageView view = findImageView(R.id.insp_card_image);

		if (view == null) {
			return;
		}

		String url = getImageUrl();

		if (TextUtils.isEmpty(url)) {
			return;
		}

		getPicasso()
			.load(url)
			.fit()
			.centerInside()
			.into(view);
	}

	private String getImageUrl() {
		List<ImageResult> images = getModel().getThumbnailUrls();

		if (images == null || images.isEmpty()) {
			return null;
		}

		ImageResult image = images.get(0);

		if (image == null) {
			return null;
		}

		return image.getURL();
	}

	private void setupOnSale(boolean value) {
		View view = findView(R.id.insp_card_badge);

		if (view != null) {
			int visibility = value ? View.VISIBLE : View.GONE;

			view.setVisibility(visibility);
		}
	}

	private void setupPrice(String value) {
		TextView view = findTextView(R.id.insp_card_price);

		if (view != null) {
			view.setText(value);
		}
	}

	private void setupPromo() {
		TextView view = findTextView(R.id.insp_card_promo);

		if (view != null) {
			view.setText(null);
		}
	}

	private void setupRating(double value) {
		RatingBar view = findRatingBar(R.id.insp_card_rating);

		if (view != null) {
			view.setRating((float) value);
		}
	}
}
