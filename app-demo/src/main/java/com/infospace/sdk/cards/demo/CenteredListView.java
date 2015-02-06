package com.infospace.sdk.cards.demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;

public class CenteredListView extends ListView {
	private int maxWidth;
	private int defaultPadding;

	public CenteredListView(Context context) {
		super(context);
		applyAttributes(context, null, 0);
	}

	public CenteredListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		applyAttributes(context, attrs, 0);
	}

	public CenteredListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		applyAttributes(context, attrs, defStyle);
	}

	private void applyAttributes(Context context, AttributeSet attrs, int defStyle) {
		if (attrs == null) {
			return;
		}

		TypedArray a = context.getTheme().obtainStyledAttributes(
			attrs,
			R.styleable.CenteredListView,
			defStyle,
			0);

		try {
			setMaxWidth(a.getDimensionPixelSize(R.styleable.CenteredListView_maxWidth, 0));
			setDefaultPadding(a.getDimensionPixelSize(R.styleable.CenteredListView_defaltPadding, 0));
		} finally {
			a.recycle();
		}
	}

	public int getMaxWidth() {
		return maxWidth;
	}

	public void setMaxWidth(int maxWidth) {
		if (maxWidth == this.maxWidth || maxWidth < 0) {
			return;
		}

		this.maxWidth = maxWidth;

		invalidate();
		requestLayout();
	}

	public int getDefaultPadding() {
		return defaultPadding;
	}

	public void setDefaultPadding(int defaultPadding) {
		if (defaultPadding == this.defaultPadding || defaultPadding < 0) {
			return;
		}

		this.defaultPadding = defaultPadding;

		invalidate();
		requestLayout();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int padding = defaultPadding;
		int width = MeasureSpec.getSize(widthMeasureSpec);

		if (width > maxWidth) {
			padding = (width - maxWidth) / 2;
		}

		setPadding(
			padding,
			getPaddingTop(),
			padding,
			getPaddingBottom()
		);

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
