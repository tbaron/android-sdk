package com.infospace.sdk.models.actions;

import com.infospace.sdk.models.*;

import java.util.regex.*;

/**
 * This action provider is used to add an "email" action to results which email addresses can be parsed out of.
 * <p/>
 *
 * @see com.infospace.sdk.models.actions.ResultActionProvider
 */
public class EmailIntentActionProvider
	extends
	ResultActionProvider {

	private static final Pattern emailPattern = Pattern.compile("\\b[-+%.\\w]+@[-+%.\\w]+\\.[-+%.\\w]+\\b");
	private final AndroidActivityStarter starter;
	private final int layoutId;

	/**
	 * Constructs an EmailIntentActionProvider.
	 *
	 * @param starter  the AndroidActivityStarter used to launch the intent using data provided by the Result.
	 * @param layoutId the Android layout resource ID which is used to create a view which triggers this action.
	 */
	public EmailIntentActionProvider(AndroidActivityStarter starter, int layoutId) {
		this.starter = starter;
		this.layoutId = layoutId;
	}

	@Override
	public OnActionListener createAction(Result result) {
		String emailAddress = getEmailAddress(result);

		if (emailAddress == null) {
			return null;
		}

		return createOnActionListener(emailAddress);
	}

	private String getEmailAddress(Result result) {
		if (result instanceof StandardResult) {
			String description = ((StandardResult) result).getPlainTextDescription();

			if (description != null) {
				Matcher matcher = emailPattern.matcher(description);

				if (matcher.find()) {
					return matcher.group();
				}
			}
		}

		return null;
	}

	private OnActionListener createOnActionListener(String emailAddress) {
		return new EmailIntentAction(this.starter, layoutId, emailAddress);
	}
}
