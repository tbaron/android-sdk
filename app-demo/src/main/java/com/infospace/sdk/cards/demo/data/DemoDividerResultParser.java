package com.infospace.sdk.cards.demo.data;

import com.infospace.sdk.models.parsers.ResultNodeParser;

import org.json.JSONObject;

public class DemoDividerResultParser extends ResultNodeParser {
	@Override
	protected DemoDividerResult createResult() {
		return new DemoDividerResult();
	}

	@Override
	protected DemoDividerResult onParse(JSONObject obj) {
		DemoDividerResult result = (DemoDividerResult) super.onParse(obj);

		result.setImageName(obj.optString("imageName"));

		return result;
	}
}
