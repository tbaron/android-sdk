package com.infospace.sdk.cards.demo.data;

import com.infospace.sdk.models.parsers.StandardResultParser;

import org.json.JSONObject;

public class DemoCategoryResultParser extends StandardResultParser {
	@Override
	protected DemoCategoryResult createResult() {
		return new DemoCategoryResult();
	}

	@Override
	protected DemoCategoryResult onParse(JSONObject obj) {
		DemoCategoryResult result = (DemoCategoryResult) super.onParse(obj);

		result.setCategory(obj.optString("category"));

		return result;
	}
}
