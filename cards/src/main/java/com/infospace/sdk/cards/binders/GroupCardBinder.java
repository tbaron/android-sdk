package com.infospace.sdk.cards.binders;

import android.view.*;

import com.infospace.sdk.Container;
import com.infospace.sdk.cards.*;
import com.infospace.sdk.models.*;

import java.util.List;

public class GroupCardBinder
	extends ViewBinder<ResultGroup> {

	private final ViewFactory viewFactory;
	private final CardBinderFactory binderFactory;

	public GroupCardBinder() {
		viewFactory = Container.resolve(ViewFactory.class);
		binderFactory = Container.resolve(CardBinderFactory.class);
	}

	@Override
	protected void onBind() {
		setupChildren(getModel().getChildren());
	}

	private void setupChildren(List<ResultNode> results) {
		ViewGroup container = findViewGroup(R.id.insp_card_results);

		if (container != null) {
			container.removeAllViews();

			for (ResultNode result : results) {
				// TODO: can we reuse existing child views?
				View view = viewFactory.getView(result, null, container);

				binderFactory.bind(result, view);

				container.addView(view);
			}
		}
	}
}
