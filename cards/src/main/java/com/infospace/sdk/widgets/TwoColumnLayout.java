package com.infospace.sdk.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.*;
import android.widget.LinearLayout;

public class TwoColumnLayout extends LinearLayout {
	private final LinearLayout[] columns;
	private Drawable dividerDrawable;
	private int childCount;

	public TwoColumnLayout(Context context) {
		super(context);
		columns = initColumns(context);
	}

	private LinearLayout[] initColumns(Context context) {
		setOrientation(HORIZONTAL);

		LinearLayout[] columns = {
			new LinearLayout(context),
			new LinearLayout(context)
		};

		for (LinearLayout column : columns) {
			column.setOrientation(VERTICAL);

			column.setDividerDrawable(dividerDrawable);
			column.setShowDividers(getShowDividers());
			column.setDividerPadding(getDividerPadding());

			super.addView(column, -1, new LayoutParams(0, LayoutParams.MATCH_PARENT, 1));
		}

		return columns;
	}

	public TwoColumnLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		columns = initColumns(context);
	}

	public TwoColumnLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		columns = initColumns(context);
	}

	@Override
	public void setShowDividers(int showDividers) {
		super.setShowDividers(showDividers);

		if (columns != null) {
			for (LinearLayout column : columns) {
				column.setShowDividers(showDividers);
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

		if (columns != null) {
			for (LinearLayout column : columns) {
				column.setDividerDrawable(divider);
			}
		}
	}

	@Override
	public void setDividerPadding(int padding) {
		super.setDividerPadding(padding);

		if (columns != null) {
			for (LinearLayout column : columns) {
				column.setDividerPadding(padding);
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
		childCount = 0;
		for (LinearLayout column : columns) {
			column.removeAllViews();
		}
	}

	private void addNextView(View child) {
		LinearLayout container = getAppropriateContainer();

		container.addView(child);

		setDividers();

		childCount++;
	}

	private LinearLayout getAppropriateContainer() {
		int index = childCount % columns.length;

		return columns[index];
	}

	private void setDividers() {
		int dividers = getShowDividers() & SHOW_DIVIDER_MIDDLE;

		if (dividers == 0) {
			return;
		}

		int index = childCount % columns.length;

		for (int i = 0; i < columns.length; i++) {
			columns[i].setShowDividers(dividers |
				(i > index ? SHOW_DIVIDER_END : SHOW_DIVIDER_NONE));
		}
	}
}
