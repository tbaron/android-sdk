package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;

import org.json.JSONObject;

public class ResultGroupParser extends ResultNodeParser {
	@Override
	protected ResultGroup onParse(JSONObject obj) {
		ResultGroup resultGroup = (ResultGroup) super.onParse(obj);

		resultGroup.setType(getType(obj));

		return resultGroup;
	}

	private ResultGroupType getType(JSONObject obj) {
		String type = obj.optString("type");

		return ResultGroupType.fromString(type);
	}

	@Override
	protected ResultNode createResult() {
		return new ResultGroup();
	}
}
