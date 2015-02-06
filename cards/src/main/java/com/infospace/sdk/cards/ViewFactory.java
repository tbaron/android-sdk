package com.infospace.sdk.cards;

import android.content.Context;
import android.util.Pair;
import android.view.*;

import com.infospace.sdk.cards.matchers.ResultMatcher;
import com.infospace.sdk.models.ResultNode;

import java.util.*;

public class ViewFactory {
	final static int INVALID_VIEW_TYPE = -1;
	final static int TAG_LAYOUT_ID = R.id.insp_tag_layout_id;

	private final LayoutInflater inflater;
	private final List<Pair<ResultMatcher, Integer>> matchers;

	/**
	 * Initializes a new {@link ViewFactory} instance.
	 *
	 * @param context A {@link Context} used to inflate views.
	 * @throws IllegalArgumentException context is null.
	 */
	public ViewFactory(Context context) {
		this.inflater = LayoutInflater.from(context);
		this.matchers = new ArrayList<Pair<ResultMatcher, Integer>>();
	}

	/**
	 * Returns a {@link View} for the specified {@link ResultNode}.
	 *
	 * @param result      The {@link ResultNode} to resolve a view for.
	 * @param convertView An old view to attempt to reuse. If it is not possible reuse this view, a
	 *                    new view will be inflated.
	 * @param parent      The parent that this view will eventually be attached to.
	 * @return A view corresponding to the specified {@link ResultNode}.
	 */
	public View getView(ResultNode result, View convertView, ViewGroup parent) {
		Integer layout = getLayoutForResult(result);

		if (layout == null) {
			return null;
		}

		if (!isViewReusable(convertView, layout)) {
			convertView = inflater.inflate(layout, parent, false);
			convertView.setTag(TAG_LAYOUT_ID, layout);
		}

		return convertView;
	}

	private Integer getLayoutForResult(ResultNode result) {
		int index = findIndexOfFirstMatch(result);

		if (index == -1) {
			return null;
		}

		return matchers.get(index).second;
	}

	private int findIndexOfFirstMatch(ResultNode result) {
		for (int i = matchers.size() - 1; i >= 0; i--) {
			if (matchers.get(i).first.matches(result)) {
				return i;
			}
		}

		return INVALID_VIEW_TYPE;
	}

	private boolean isViewReusable(View view, int preferredLayout) {
		if (view == null) {
			return false;
		}

		Object tag = view.getTag(TAG_LAYOUT_ID);

		return tag instanceof Integer && (Integer) tag == preferredLayout;
	}

	/**
	 * Registers a new {@link ResultMatcher} which resolves to the specified {@param layoutId}.
	 * Registration order matters.
	 *
	 * @param matcher  A {@link ResultMatcher} which will match a result.
	 * @param layoutId The ID of a layout resource to return when the {@param matcher} matches.
	 * @throws IllegalArgumentException {@param matcher} is null -or- layoutId is invalid.
	 */
	public ViewFactory push(ResultMatcher matcher, int layoutId) {
		if (matcher == null || layoutId == 0) {
			throw new IllegalArgumentException();
		}

		matchers.add(Pair.create(matcher, layoutId));

		return this;
	}

	/**
	 * Clears all previously registered {@link ResultMatcher}s.
	 */
	public void clear() {
		matchers.clear();
	}

	/**
	 * Internally used by {@link CardAdapter}.
	 */
	int getItemViewType(ResultNode result) {
		return findIndexOfFirstMatch(result);
	}

	/**
	 * Internally used by {@link CardAdapter}.
	 */
	int getViewTypeCount() {
		return matchers.size();
	}
}
