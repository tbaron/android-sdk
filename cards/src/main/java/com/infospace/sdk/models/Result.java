package com.infospace.sdk.models;

import com.infospace.sdk.models.actions.OnActionListener;

import java.util.List;

public abstract class Result extends ResultNode {
	private transient List<OnActionListener> actions;

	/**
	 * A result may contain data which is usable by external applications, such as a phone number to call or an email address to send a message to.  Requires the appropriate ResultActionProvider(s) to be registered on startup.
	 *
	 * @return the list of actions parsed out of this result.  If no actions were parsed, this returns an empty list
	 * @see com.infospace.sdk.models.actions.ResultActionProvider
	 * @see com.infospace.sdk.models.actions.OnActionListener
	 */
	public List<OnActionListener> getActions() {
		return actions;
	}

	public void setActions(List<OnActionListener> actions) {
		this.actions = actions;
	}
}
