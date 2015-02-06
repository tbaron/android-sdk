package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.ResultNode;

import org.json.JSONObject;

public interface ResultParser {
	ResultNode parseResult(JSONObject result);
}
