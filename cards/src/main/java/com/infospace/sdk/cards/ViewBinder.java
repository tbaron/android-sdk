package com.infospace.sdk.cards;

import android.view.*;
import android.widget.*;

import com.infospace.sdk.Container;
import com.squareup.picasso.Picasso;

/**
 * Represents a creator class that knows how to populate a view with a data model.
 *
 * @param <T> The type of data model which this creator supports.
 */
public abstract class ViewBinder<T> {
	protected interface OnImageViewMeasuredListener {
		void onImageViewMeasured(ImageView view);
	}

	private final Picasso picasso;
	private T model;
	private int modelHashCode;
	private View view;
	private LayoutInflater inflater;

	protected ViewBinder() {
		picasso = Container.resolve(Picasso.class);
	}

	protected Picasso getPicasso() {
		return picasso;
	}

	protected T getModel() {
		return model;
	}

	protected LayoutInflater getInflater() {
		return inflater;
	}

	/**
	 * Populates a {@link View} with data from the specified {@link T} model.
	 */
	public final void bind(T model, View view) {
		if (model != null && view != null) {
			this.model = model;
			this.modelHashCode = model.hashCode();
			this.view = view;

			if (!isAlreadyBound()) {
				setBoundModel();
				initInflater();
				onBind();
			}
		}
	}

	private boolean isAlreadyBound() {
		Object tag = view.getTag(R.id.insp_tag_model_id);

		return tag instanceof Integer && (Integer) tag == modelHashCode;
	}

	private void setBoundModel() {
		view.setTag(R.id.insp_tag_model_id, modelHashCode);
	}

	private void initInflater() {
		inflater = LayoutInflater.from(view.getContext());
	}

	protected abstract void onBind();

	protected void setImageViewMeasuredListener(int id, final OnImageViewMeasuredListener listener) {
		final ImageView view = findImageView(id);

		if (view == null) {
			return;
		}

		view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
			@Override
			public boolean onPreDraw() {
				view.getViewTreeObserver().removeOnPreDrawListener(this);

				listener.onImageViewMeasured(view);

				return true;
			}
		});
	}

	protected ImageView findImageView(int id) {
		View v = getView().findViewById(id);

		if (v instanceof ImageView) {
			return (ImageView) v;
		}

		return null;
	}

	protected View getView() {
		return view;
	}

	protected RatingBar findRatingBar(int id) {
		View v = getView().findViewById(id);

		if (v instanceof RatingBar) {
			return (RatingBar) v;
		}

		return null;
	}

	protected TextView findTextView(int id) {
		View v = view.findViewById(id);

		if (v instanceof TextView) {
			return (TextView) v;
		}

		return null;
	}

	protected View findView(int id) {
		return view.findViewById(id);
	}

	protected ViewGroup findViewGroup(int id) {
		View v = view.findViewById(id);

		if (v instanceof ViewGroup) {
			return (ViewGroup) v;
		}

		return null;
	}
}
