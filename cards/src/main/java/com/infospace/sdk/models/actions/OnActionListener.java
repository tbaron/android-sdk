package com.infospace.sdk.models.actions;


/**
 * A utility class which allows a View (specified by Layout Id) to execute an action which is contextually relevant to the Result.
 */
public interface OnActionListener {
	/**
	 * This is the view id specified in the registration of ResultActionProviders.  This view id can be used to cause interaction with a view to result in a specific action.
	 *
	 * @return
	 */
	int getLayoutId();

	/**
	 * The action to perform.  This can be used in conjunction with getLayoutId to execute onAction() code when a view is interacted with.
	 */
	void onAction();
}
