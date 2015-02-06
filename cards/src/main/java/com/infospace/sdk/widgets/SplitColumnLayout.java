package com.infospace.sdk.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.*;
import android.widget.LinearLayout;

import java.util.*;

public class SplitColumnLayout extends LinearLayout {
	private static final int NUM_COLUMNS = 2;

	private List<LinearLayout> rows = new ArrayList<LinearLayout>();
	private List<View> children = new ArrayList<View>();
	private Drawable dividerDrawable;

	public SplitColumnLayout(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		setOrientation(VERTICAL);
	}

	public SplitColumnLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public SplitColumnLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	@Override
	public void setShowDividers(int showDividers) {
		super.setShowDividers(showDividers);

		if (rows != null) {
			for (LinearLayout row : rows) {
//				row.setShowDividers(showDividers & SHOW_DIVIDER_MIDDLE);
				row.setShowDividers(showDividers);
			}
		}
	}

	@Override
	public void setDividerDrawable(Drawable divider) {
		super.setDividerDrawable(divider);

		if (divider == dividerDrawable) {
			return;
		}

		dividerDrawable = divider;

		if (rows != null) {
			for (LinearLayout row : rows) {
				row.setDividerDrawable(divider);
			}
		}
	}

	@Override
	public void setDividerPadding(int padding) {
		super.setDividerPadding(padding);

		if (rows != null) {
			for (LinearLayout row : rows) {
				row.setDividerPadding(padding);
			}
		}
	}

	@Override
	public void addView(View child) {
		addNextView(child);
	}

	@Override
	public void addView(View child, int index) {
		addNextView(child);
	}

	@Override
	public void addView(View child, int width, int height) {
		addNextView(child);
	}

	@Override
	public void addView(View child, ViewGroup.LayoutParams params) {
		addNextView(child);
	}

	@Override
	public void addView(View child, int index, ViewGroup.LayoutParams params) {
		addNextView(child);
	}

	@Override
	public void removeAllViews() {
		super.removeAllViews();

		for (LinearLayout row : rows) {
			row.removeAllViews();
		}

		children.clear();
	}

	private void addNextView(View child) {
		children.add(child);

		layoutChildren();
	}

	private void layoutChildren() {
		int count = children.size();
		int remainder = count % NUM_COLUMNS;

		for (ViewGroup row : rows) {
			row.removeAllViews();
		}

		if ((float) count / NUM_COLUMNS > rows.size()) {
			addRow();
		}

		ViewGroup row = rows.get(0);
		LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);

		// First row
		for (int i = 0; i < remainder; i++) {
			row.addView(children.get(i), params);
		}

		for (int i = remainder; i < count; i++) {
			if (i % NUM_COLUMNS == remainder) {
				row = rows.get((i + remainder) / NUM_COLUMNS);
			}

			row.addView(children.get(i), params);
		}
	}

	private LinearLayout addRow() {
		LinearLayout row = new LinearLayout(getContext());

		row.setOrientation(HORIZONTAL);

		row.setDividerDrawable(dividerDrawable);
//		row.setShowDividers(getShowDividers() & SHOW_DIVIDER_MIDDLE);
		row.setShowDividers(getShowDividers());
		row.setDividerPadding(getDividerPadding());

		super.addView(row, -1, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		rows.add(row);

		return row;
	}
}
