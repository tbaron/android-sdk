package com.infospace.sdk.models.parsers.matchers;

import org.json.JSONObject;

public class TypeFieldJsonMatcher implements JsonMatcher {
	private final String typeName;

	public TypeFieldJsonMatcher(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public boolean matches(JSONObject json) {
		String jsonType = json.optString("type", null);

		if (typeName == null) {
			return jsonType == null;
		}

		return typeName.equalsIgnoreCase(jsonType);
	}
}
