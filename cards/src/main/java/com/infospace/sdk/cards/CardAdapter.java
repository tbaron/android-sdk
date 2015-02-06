package com.infospace.sdk.cards;

import android.view.*;
import android.widget.BaseAdapter;

import com.infospace.sdk.Container;
import com.infospace.sdk.models.ResultNode;

import java.util.List;

public class CardAdapter extends BaseAdapter {
	private final List<? extends ResultNode> results;
	private final CardBinderFactory binderFactory;
	private final ViewFactory viewFactory;

	/**
	 * Initializes a new ResultAdapter with the specified list of {@link ResultNode}s.
	 *
	 * @param results A list of {@link ResultNode}s for which views will be created.
	 * @throws IllegalArgumentException context -or- results is null.
	 */
	public CardAdapter(List<? extends ResultNode> results) {
		if (results == null) {
			throw new IllegalArgumentException();
		}

		this.results = results;
		this.binderFactory = Container.resolve(CardBinderFactory.class);
		this.viewFactory = Container.resolve(ViewFactory.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCount() {
		return results.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResultNode getItem(int position) {
		return results.get(position);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getItemId(int position) {
		return results.get(position).hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ResultNode node = getItem(position);

		convertView = viewFactory.getView(node, convertView, parent);

		binderFactory.bind(node, convertView);

		return convertView;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getItemViewType(int position) {
		return viewFactory.getItemViewType(getItem(position));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getViewTypeCount() {
		return viewFactory.getViewTypeCount();
	}
}
