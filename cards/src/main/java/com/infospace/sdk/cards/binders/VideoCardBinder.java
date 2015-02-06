package com.infospace.sdk.cards.binders;

import android.widget.*;

import com.infospace.sdk.cards.R;
import com.infospace.sdk.models.VideoResult;

public class VideoCardBinder extends StandardCardBinder<VideoResult> {
	@Override
	protected void onBind() {
		super.onBind();

		setupDuration();
		setupImage();
	}

	private void setupDuration() {
		TextView view = findTextView(R.id.insp_card_duration);

		if (view != null) {
			String time = getModel().getDisplayDuration();

			view.setText(time);
		}
	}

	private void setupImage() {
		ImageView view = findImageView(R.id.insp_card_image);

		if (view != null) {
			getPicasso()
				.load(getModel().getThumbnailUrl())
				.fit()
				.transform(new VideoImageTransformation(view.getResources()))
				.into(view);
		}
	}
}
