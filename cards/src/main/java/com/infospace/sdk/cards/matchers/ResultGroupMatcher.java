package com.infospace.sdk.cards.matchers;

import com.infospace.sdk.models.*;

public class ResultGroupMatcher implements ResultMatcher {
	private final ResultGroupType type;

	public ResultGroupMatcher(ResultGroupType type) {
		this.type = type;
	}

	@Override
	public boolean matches(ResultNode result) {
		return result instanceof ResultGroup && ((ResultGroup) result).getType() == type;
	}
}
