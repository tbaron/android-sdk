package com.infospace.sdk.cards.demo.data;

import android.view.View;
import android.widget.ImageView;

import com.infospace.sdk.cards.ViewBinder;
import com.infospace.sdk.cards.demo.R;

public class DemoDividerViewBinder extends ViewBinder<DemoDividerResult> {
	@Override
	protected void onBind() {
		setupImage(getModel().getImageName());
	}

	private void setupImage(String imageName) {
		ImageView view = findImageView(R.id.insp_card_image);

		if (view == null) {
			return;
		}

		int drawableId = findResourceId("drawable/" + imageName);

		if (drawableId == 0) {
			view.setVisibility(View.GONE);
			return;
		}

		view.setVisibility(View.VISIBLE);

		getPicasso()
			.load(drawableId)
			.into(view);
	}

	private int findResourceId(String imageName) {
		View view = getView();
		String packageName = view.getContext().getPackageName();

		return view.getResources().getIdentifier(imageName, null, packageName);
	}
}
