package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;

import org.json.JSONObject;

public class SpellingResultParser extends BaseResultParser {
	/**
	 * Populates a spelling result object with the following fields
	 * including the applicable fields from <code>StandardResultParser</code>.
	 * <ul>
	 * <li>Title</li>
	 * </ul>
	 *
	 * @param obj spelling JSON response
	 * @return spelling result object
	 * @see StandardResultParser#parseResult(JSONObject)
	 */
	@Override
	protected SpellingResult onParse(JSONObject obj) {
		SpellingResult result = (SpellingResult) super.onParse(obj);
		result.setTitle(obj.optString("title"));
		return result;
	}

	@Override
	protected Result createResult() {
		return new SpellingResult();
	}
}
