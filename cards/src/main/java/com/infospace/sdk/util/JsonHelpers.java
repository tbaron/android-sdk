package com.infospace.sdk.util;

import org.json.*;

public abstract class JsonHelpers {
	public static JsonObjectCollection getCollection(JSONObject obj, String name) {
		JSONArray array = obj.optJSONArray(name);

		if (array == null) {
			return null;
		}

		return new JsonObjectCollection(array);
	}
}
