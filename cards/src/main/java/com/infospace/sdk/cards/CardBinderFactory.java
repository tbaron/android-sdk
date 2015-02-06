package com.infospace.sdk.cards;

import android.util.Pair;
import android.view.View;

import com.infospace.sdk.cards.matchers.ResultMatcher;
import com.infospace.sdk.models.ResultNode;

import java.util.*;

/**
 * Responsible for binding a {@link ResultNode} to a {@link View}. Call #register(ResultMatcher, Class)
 * to register binders.
 */
public class CardBinderFactory {
	private final List<Pair<ResultMatcher, Class<? extends ViewBinder<? extends ResultNode>>>> matchers;

	public CardBinderFactory() {
		matchers = new ArrayList<Pair<ResultMatcher, Class<? extends ViewBinder<? extends ResultNode>>>>();
	}

	/**
	 * Registers a new instance of the specified binder for each result that matches the specified matcher.
	 * The last-added matcher will take precedence over existing matchers.
	 */
	public CardBinderFactory push(ResultMatcher matcher, Class<? extends ViewBinder<? extends ResultNode>> binder) {
		matchers.add(new Pair<ResultMatcher, Class<? extends ViewBinder<? extends ResultNode>>>(matcher, binder));

		return this;
	}

	public void bind(ResultNode result, View view) {
		ViewBinder binder = findBinder(result);

		if (binder != null) {
			try {
				bindUnchecked(binder, view, result);
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void bindUnchecked(ViewBinder binder, View view, ResultNode result) {
		binder.bind(result, view);
	}

	private ViewBinder findBinder(ResultNode result) {
		Class<? extends ViewBinder> binderClass = findBinderClass(result);

		if (binderClass != null) {
			try {
				return binderClass.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private Class<? extends ViewBinder> findBinderClass(ResultNode result) {
		for (int i = matchers.size() - 1; i >= 0; i--) {
			Pair<ResultMatcher, Class<? extends ViewBinder<? extends ResultNode>>> pair = matchers.get(i);

			if (pair.first.matches(result)) {
				return pair.second;
			}
		}

		return null;
	}
}
