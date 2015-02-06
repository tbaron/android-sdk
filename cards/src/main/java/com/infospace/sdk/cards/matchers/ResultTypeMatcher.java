package com.infospace.sdk.cards.matchers;

import com.infospace.sdk.models.ResultNode;

public class ResultTypeMatcher implements ResultMatcher {
	private final Class<? extends ResultNode> type;

	public ResultTypeMatcher(Class<? extends ResultNode> type) {
		if (type == null) {
			throw new IllegalArgumentException();
		}

		this.type = type;
	}

	@Override
	public boolean matches(ResultNode result) {
		return result != null && type.isAssignableFrom(result.getClass());
	}
}
