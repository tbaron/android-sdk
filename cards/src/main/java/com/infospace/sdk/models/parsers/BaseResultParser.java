package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;
import com.infospace.sdk.models.actions.ResultActionProvider;

import org.json.JSONObject;

public abstract class BaseResultParser extends ResultNodeParser {

	@Override
	protected Result onParse(JSONObject obj) {
		if (obj == null) {
			throw new IllegalArgumentException("obj is null");
		}

		return (Result) super.onParse(obj);
	}

	@Override
	protected void onPostParse(ResultNode node) {
		Result result = (Result) node;

		result.setActions(ResultActionProvider.getResultActions(result));

		super.onPostParse(result);
	}

}
