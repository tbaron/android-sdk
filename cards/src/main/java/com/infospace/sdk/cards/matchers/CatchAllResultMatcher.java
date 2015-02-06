package com.infospace.sdk.cards.matchers;

import com.infospace.sdk.models.ResultNode;

public class CatchAllResultMatcher implements ResultMatcher {
	@Override
	public boolean matches(ResultNode result) {
		return true;
	}
}
