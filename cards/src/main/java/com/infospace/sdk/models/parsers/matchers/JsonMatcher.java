package com.infospace.sdk.models.parsers.matchers;

import org.json.JSONObject;

public interface JsonMatcher {
	boolean matches(JSONObject json);
}
