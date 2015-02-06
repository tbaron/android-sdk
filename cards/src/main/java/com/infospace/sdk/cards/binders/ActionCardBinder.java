package com.infospace.sdk.cards.binders;

import android.view.View;

import com.infospace.sdk.cards.ViewBinder;
import com.infospace.sdk.models.actions.OnActionListener;

public class ActionCardBinder extends ViewBinder<OnActionListener> {
	@Override
	protected void onBind() {
		setupOnClick();
	}

	private void setupOnClick() {
		final OnActionListener action = getModel();

		getView().setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				action.onAction();
			}
		});
	}
}
