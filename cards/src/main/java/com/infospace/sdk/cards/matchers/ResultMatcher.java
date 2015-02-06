package com.infospace.sdk.cards.matchers;

import com.infospace.sdk.models.ResultNode;

public interface ResultMatcher {
	boolean matches(ResultNode result);
}
