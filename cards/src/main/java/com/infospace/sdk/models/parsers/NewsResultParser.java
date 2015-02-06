package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;

import org.json.JSONObject;

import java.util.Date;

public class NewsResultParser extends StandardResultParser {
	/**
	 * Populates a news result object with the following fields
	 * including the applicable fields from <code>StandardResultParser</code>.
	 * <ul>
	 * <li>Artical Date</li>
	 * <li>Artical Source</li>
	 * </ul>
	 *
	 * @param obj news JSON response
	 * @return news result object
	 * @see StandardResultParser#parseResult(JSONObject)
	 */
	@Override
	protected NewsResult onParse(JSONObject obj) {
		NewsResult result = (NewsResult) super.onParse(obj);

		result.setDate(getDate(obj));
		result.setSource(getSource(obj));
		result.setThumbnailUrl(getThumbnailUrl(obj));

		return result;
	}

	private Date getDate(JSONObject obj) {
		long milliseconds = obj.optLong("date");

		return createDate(milliseconds);
	}

	private Date createDate(long milliseconds) {
		if (milliseconds <= 0) {
			return null;
		}

		return new Date(milliseconds);
	}

	private String getSource(JSONObject obj) {
		return obj.optString("source");
	}

	private String getThumbnailUrl(JSONObject obj) {
		return obj.optString("thumb_url");
	}

	@Override
	protected Result createResult() {
		return new NewsResult();
	}
}
