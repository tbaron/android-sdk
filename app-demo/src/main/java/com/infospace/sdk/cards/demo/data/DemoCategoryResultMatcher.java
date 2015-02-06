package com.infospace.sdk.cards.demo.data;

import com.infospace.sdk.cards.matchers.ResultMatcher;
import com.infospace.sdk.models.ResultNode;

public class DemoCategoryResultMatcher implements ResultMatcher {
	private final String category;

	public DemoCategoryResultMatcher(String category) {
		this.category = category;
	}

	@Override
	public boolean matches(ResultNode result) {
		return result instanceof DemoCategoryResult && category.equalsIgnoreCase(((DemoCategoryResult) result).getCategory());
	}
}
