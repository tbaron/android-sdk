package com.infospace.sdk.cards.demo.data;

import com.infospace.sdk.models.parsers.ResultNodeParser;

import org.json.JSONObject;

public class DemoHeaderResultParser extends ResultNodeParser {
	@Override
	protected DemoHeaderResult createResult() {
		return new DemoHeaderResult();
	}

	@Override
	protected DemoHeaderResult onParse(JSONObject obj) {
		DemoHeaderResult result = (DemoHeaderResult) super.onParse(obj);

		result.setTitle(obj.optString("title"));
		result.setDescription(obj.optString("description"));

		return result;
	}
}
