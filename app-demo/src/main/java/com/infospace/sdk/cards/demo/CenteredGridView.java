package com.infospace.sdk.cards.demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.GridView;

public class CenteredGridView extends GridView {
	private int defaultPadding;
	private int horizontalSpacing;
	private int columnWidth;
	private int numColumns;

	public CenteredGridView(Context context) {
		super(context);
		applyAttributes(context, null, 0);
	}

	public CenteredGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		applyAttributes(context, attrs, 0);
	}

	public CenteredGridView(Context context, AttributeSet attrs, int defStyle) {
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
			setDefaultPadding(a.getDimensionPixelSize(R.styleable.CenteredListView_defaltPadding, 0));
		} finally {
			a.recycle();
		}
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
	public void setHorizontalSpacing(int horizontalSpacing) {
		super.setHorizontalSpacing(horizontalSpacing);

		this.horizontalSpacing = horizontalSpacing;
	}

	@Override
	public void setColumnWidth(int columnWidth) {
		super.setColumnWidth(columnWidth);

		this.columnWidth = columnWidth;
	}

	@Override
	public void setNumColumns(int numColumns) {
		super.setNumColumns(numColumns);

		this.numColumns = numColumns;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (columnWidth > 0 && numColumns == AUTO_FIT) {
			int width = MeasureSpec.getSize(widthMeasureSpec)
				- defaultPadding
				- defaultPadding;

			int columnWithSpacing = columnWidth + horizontalSpacing;
			int contentWidth = columnWidth;

			while (contentWidth + columnWithSpacing <= width) {
				contentWidth += columnWithSpacing;
			}

			int padding = defaultPadding + (width - contentWidth) / 2;


			setPadding(
				padding,
				getPaddingTop(),
				padding,
				getPaddingBottom()
			);
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
