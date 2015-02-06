package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;

import org.json.JSONObject;

public class RelatedResultParser extends BaseResultParser {
	@Override
	protected RelatedResult onParse(JSONObject obj) {
		RelatedResult result = (RelatedResult) super.onParse(obj);
		result.setTitle(obj.optString("title"));
		return result;
	}

	@Override
	protected Result createResult() {
		return new RelatedResult();
	}
}
