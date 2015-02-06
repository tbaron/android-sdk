package com.infospace.sdk.cards.binders;

import android.widget.RatingBar;

import com.infospace.sdk.cards.R;
import com.infospace.sdk.models.AdResult;

public abstract class StandardAdCardBinder<TResult extends AdResult>
	extends StandardCardBinder<TResult> {

	@Override
	protected void onBind() {
		super.onBind();

		setupRating(getModel().getRating());
	}

	private void setupRating(double rating) {
		RatingBar ratingBar = findRatingBar(R.id.insp_card_rating);

		if (ratingBar != null) {
			ratingBar.setRating((float) rating);
		}
	}
}
