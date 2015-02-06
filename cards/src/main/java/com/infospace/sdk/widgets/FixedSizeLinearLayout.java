package com.infospace.sdk.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.infospace.sdk.cards.R;

public class FixedSizeLinearLayout
	extends LinearLayout {

	private double numVisibleItems;
	private Drawable dividerDrawable;

	public FixedSizeLinearLayout(Context context) {
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
			R.styleable.FixedSizeLinearLayout,
			defStyle,
			0);

		try {
			numVisibleItems = a.getFloat(R.styleable.FixedSizeLinearLayout_numVisibleItems, 0);
		} finally {
			a.recycle();
		}
	}

	public FixedSizeLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		init(context, attrs, 0);
	}

	public FixedSizeLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs, defStyle);
	}

	@SuppressWarnings("unused")
	public double getNumVisibleItems() {
		return numVisibleItems;
	}

	@SuppressWarnings("unused")
	public void setNumVisibleItems(double numVisibleItems) {
		this.numVisibleItems = numVisibleItems;
		invalidate();
		requestLayout();
	}

	@Override
	public void setDividerDrawable(Drawable divider) {
		this.dividerDrawable = divider;

		super.setDividerDrawable(divider);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (hasValidNumVisibleItems()) {
			setChildSize(widthMeasureSpec, heightMeasureSpec);

			// TODO: this seems like a hack
			if (getOrientation() == VERTICAL) {
				heightMeasureSpec = removeMeasureSpecConstraint(heightMeasureSpec);
			} else {
				widthMeasureSpec = removeMeasureSpecConstraint(widthMeasureSpec);
			}
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		forceSecondMeasureIfRequiredDimensionUnspecified(widthMeasureSpec, heightMeasureSpec);
	}

	private void forceSecondMeasureIfRequiredDimensionUnspecified(int widthMeasureSpec, int heightMeasureSpec) {
		if (getOrientation() == VERTICAL) {
			if (heightMeasureSpec == 0) {
				setMeasuredDimension(getMeasuredWidth(), 0);
			}
		} else {
			if (widthMeasureSpec == 0) {
				setMeasuredDimension(0, getMeasuredHeight());
			}
		}
	}

	private int removeMeasureSpecConstraint(int measureSpec) {
		return MeasureSpec.makeMeasureSpec(
			MeasureSpec.getSize(measureSpec),
			MeasureSpec.UNSPECIFIED);
	}

	private boolean hasValidNumVisibleItems() {
		return numVisibleItems > 0;
	}

	private void setChildSize(int widthMeasureSpec, int heightMeasureSpec) {
		if (getOrientation() == VERTICAL) {
			setChildHeight(heightMeasureSpec);
		} else {
			setChildWidth(widthMeasureSpec);
		}
	}

	private void setChildHeight(int measureSpec) {
		if (isMeasureSpecUnspecified(measureSpec)) {
			return;
		}

		int size = calculateChildSize(measureSpec);

		for (int i = getChildCount() - 1; i >= 0; i--) {
			ViewGroup.LayoutParams params = getChildAt(i).getLayoutParams();

			params.width = LayoutParams.MATCH_PARENT;
			params.height = size;
		}
	}

	private boolean isMeasureSpecUnspecified(int measureSpec) {
		return measureSpec == 0;
	}

	private void setChildWidth(int measureSpec) {
		if (isMeasureSpecUnspecified(measureSpec)) {
			return;
		}

		int size = calculateChildSize(measureSpec);

		for (int i = getChildCount() - 1; i >= 0; i--) {
			ViewGroup.LayoutParams params = getChildAt(i).getLayoutParams();

			params.width = size;
			params.height = LayoutParams.MATCH_PARENT;
		}
	}

	private int calculateChildSize(int measureSpec) {
		double size = getSizeFromMeasureSpec(measureSpec);
		double divider = getDividerSize() / 2;

		return (int) (size - divider);
	}

	private double getSizeFromMeasureSpec(int measureSpec) {
		double size = MeasureSpec.getSize(measureSpec);

		return size / numVisibleItems;
	}

	private int getDividerSize() {
		Drawable divider = this.dividerDrawable;

		if (divider == null) {
			return 0;
		}

		int value = getOrientation() == VERTICAL
			? divider.getIntrinsicHeight()
			: divider.getIntrinsicWidth();

		if (value < 0) {
			value = 0;
		}

		return value;
	}
}
