package com.infospace.sdk.cards.events;

import android.view.View;

import com.infospace.sdk.Container;
import com.infospace.sdk.models.Result;

public class ViewEventAdapter implements
	View.OnClickListener,
	View.OnLongClickListener {

	private final Result result;

	public ViewEventAdapter(Result result) {
		this.result = result;
	}

	@Override
	public void onClick(View v) {
		CardClickEvent event = new CardClickEvent();
		event.result = result;

		getDispatcher().onCardClick(event);
	}

	private CardEventDispatcher getDispatcher() {
		return Container.resolve(CardEventDispatcher.class);
	}

	@Override
	public boolean onLongClick(View v) {
		CardLongClickEvent event = new CardLongClickEvent();
		event.result = result;

		getDispatcher().onCardLongClick(event);

		return false;
	}

	public void onLoad() {
		CardLoadEvent event = new CardLoadEvent();
		event.result = result;

		getDispatcher().onCardLoad(event);
	}
}
