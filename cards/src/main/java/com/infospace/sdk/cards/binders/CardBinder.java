package com.infospace.sdk.cards.binders;

import android.view.*;

import com.infospace.sdk.Container;
import com.infospace.sdk.cards.*;
import com.infospace.sdk.cards.events.ViewEventAdapter;
import com.infospace.sdk.models.Result;
import com.infospace.sdk.models.actions.OnActionListener;

import java.util.List;

public abstract class CardBinder<TResult extends Result>
	extends ViewBinder<TResult> {

	private final ViewFactory viewFactory;
	private final CardBinderFactory binderFactory;

	public CardBinder() {
		viewFactory = Container.resolve(ViewFactory.class);
		binderFactory = Container.resolve(CardBinderFactory.class);
	}

	@Override
	protected void onBind() {
		TResult result = getModel();

		setupActions(result.getActions());
		setupEvents(result);
	}

	private void setupEvents(TResult result) {
		View view = getView();
		ViewEventAdapter eventAdapter = new ViewEventAdapter(result);

		view.setOnClickListener(eventAdapter);
		view.setOnLongClickListener(eventAdapter);

		eventAdapter.onLoad();
	}

	private void setupActions(List<OnActionListener> actions) {
		ViewGroup container = findViewGroup(R.id.insp_card_actions);

		if (container == null) {
			return;
		}

		container.removeAllViews();

		if (actions == null || actions.isEmpty()) {
			container.setVisibility(View.GONE);
		} else {
			container.setVisibility(View.VISIBLE);

			for (OnActionListener action : actions) {
				container.addView(createActionView(action, container));
			}
		}

	}

	private View createActionView(final OnActionListener action, ViewGroup parent) {
		View view = getInflater().inflate(action.getLayoutId(), parent, false);

		new ActionCardBinder().bind(action, view);

		return view;
	}

	protected ViewFactory getViewFactory() {
		return viewFactory;
	}

	protected CardBinderFactory getBinderFactory() {
		return binderFactory;
	}
}
