package com.infospace.sdk.models.actions;

import com.infospace.sdk.models.*;

/**
 * This action provider is used to add a "directions" action to results which support directions.
 * <p/>
 *
 * @see com.infospace.sdk.models.actions.ResultActionProvider
 */
public class DirectionsIntentActionProvider
	extends ResultActionProvider {

	private final AndroidActivityStarter starter;
	private final int layoutId;

	/**
	 * Constructs a DirectionsIntentActionProvider.
	 *
	 * @param starter  the AndroidActivityStarter used to launch the intent using data provided by the Result.
	 * @param layoutId the Android layout resource ID which is used to create a view which triggers this action.
	 */
	public DirectionsIntentActionProvider(AndroidActivityStarter starter, int layoutId) {
		this.starter = starter;
		this.layoutId = layoutId;
	}

	@Override
	public OnActionListener createAction(Result result) {
		if (result instanceof LocalAdResult) {
			return createAction((LocalAdResult) result);
		}
		return null;
	}

	/**
	 * @hide
	 */
	private OnActionListener createAction(LocalAdResult result) {
		if (result.getDirectionsUrl() == null) {
			return null;
		}

		return new DirectionsIntentAction(starter, layoutId, result.getAddress(), result.getPlainTextTitle());
	}
}
