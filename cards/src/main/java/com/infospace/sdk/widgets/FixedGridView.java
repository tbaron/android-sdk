package com.infospace.sdk.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.GridView;

import com.infospace.sdk.cards.R;

public class FixedGridView extends GridView {

	public static final int BORDER_NONE = 0;
	public static final int BORDER_TOP = 1;
	public static final int BORDER_RIGHT = 2;
	public static final int BORDER_BOTTOM = 4;
	public static final int BORDER_LEFT = 8;
	public static final int BORDER_OUTSIDE = 15;
	public static final int BORDER_INSIDE_HORIZONTAL = 16;
	public static final int BORDER_INSIDE_VERTICAL = 32;
	public static final int BORDER_INSIDE = 48;
	public static final int BORDER_ALL = 63;

	private int border;

	public FixedGridView(Context context) {
		super(context);
		init(context, null, 0);
	}

	private void init(Context context, AttributeSet attrs, int defStyle) {
		applyAttributes(context, attrs, defStyle);
	}

	private void applyAttributes(Context context, AttributeSet attrs, int defStyle) {
		if (attrs == null) {
			return;
		}

		TypedArray a = context.getTheme().obtainStyledAttributes(
			attrs,
			R.styleable.FixedGridView,
			defStyle,
			0);

		try {
			setBackgroundResource(a.getResourceId(R.styleable.FixedGridView_border_color, 0));
			setBorder(a.getInt(R.styleable.FixedGridView_border, BORDER_NONE));
		} finally {
			a.recycle();
		}
	}

	public FixedGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, 0);
	}

	public FixedGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs, defStyle);
	}

	public int getBorder() {
		return border;
	}

	public void setBorder(int border) {
		if (border < BORDER_NONE) {
			border = BORDER_NONE;
		} else if (border >= BORDER_ALL) {
			border = BORDER_ALL;
		}

		this.border = border;

		setPadding(
			getBorder(BORDER_LEFT),
			getBorder(BORDER_TOP),
			getBorder(BORDER_RIGHT),
			getBorder(BORDER_BOTTOM)
		);

		setHorizontalSpacing(
			getBorder(BORDER_INSIDE_VERTICAL)
		);

		setHorizontalSpacing(
			getBorder(BORDER_INSIDE_HORIZONTAL)
		);
	}

	private int getBorder(int flag) {
		return (border & flag) == 0
			? 0
			: 1;
	}

//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, MeasureSpec.UNSPECIFIED);
//	}
}
