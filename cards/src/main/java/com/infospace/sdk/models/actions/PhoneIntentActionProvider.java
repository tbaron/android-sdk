package com.infospace.sdk.models.actions;

import com.infospace.sdk.models.Result;

/**
 * This action provider is used to add a "call" action to results which a phone number can be parsed out of.
 * <p/>
 *
 * @see com.infospace.sdk.models.actions.ResultActionProvider
 */
public class PhoneIntentActionProvider
	extends
	ResultActionProvider {

	private final AndroidActivityStarter starter;
	private final int layoutId;
	private ResultPhoneNumberExtractor phoneNumberExtractor;

	/**
	 * Constructs a PhoneIntentActionProvider.
	 *
	 * @param starter  the AndroidActivityStarter used to launch the intent using data provided by the Result.
	 * @param layoutId the Android layout resource ID which is used to create a view which triggers this action.
	 */
	public PhoneIntentActionProvider(AndroidActivityStarter starter, int layoutId) {
		this.starter = starter;
		this.layoutId = layoutId;
		phoneNumberExtractor = new ResultPhoneNumberExtractor();
	}

	@Override
	public OnActionListener createAction(Result result) {
		String phoneNumber = phoneNumberExtractor.getPhoneNumber(result);
		if (phoneNumber != null) {
			return new CallIntentAction(this.starter, layoutId, phoneNumber);
		}
		return null;
	}
}
