package com.infospace.sdk.models.parsers;

import com.infospace.sdk.models.*;

import org.json.JSONObject;

import java.util.regex.Pattern;

public class VideoResultParser extends StandardResultParser {
	private static final Pattern invalidUrl = Pattern.compile("\\.(swf|flv)$", Pattern.CASE_INSENSITIVE);

	/**
	 * Populates the video result object with the following fields
	 * including the applicable fields from <code>StandardResultParser</code>.
	 * <ul>
	 * <li>Video Format</li>
	 * <li>Thumbnail URL</li>
	 * <li>Video Duration</li>
	 * </ul>
	 *
	 * @param obj image JSON response
	 * @return image result object
	 * @see StandardResultParser#parseResult(JSONObject)
	 */
	@Override
	protected VideoResult onParse(JSONObject obj) {
		VideoResult result = (VideoResult) super.onParse(obj);

		result.setFormat(getFormat(obj));
		result.setThumbnailUrl(getThumbnailUrl(obj));
		result.setDuration(getDuration(obj));

		return result;
	}

	private String getFormat(JSONObject obj) {
		return obj.optString("format");
	}

	private static String getThumbnailUrl(JSONObject obj) {
		String url = obj.optString("thumb_url");

		if (isUnusableThumbnail(url)) {
			url = obj.optString("thumb_url_alt", null);
		}

		return url;
	}

	private static boolean isUnusableThumbnail(String url) {
		return invalidUrl.matcher(url).find();
	}

	private static int getDuration(JSONObject obj) {
		return obj.optInt("length");
	}

	@Override
	protected Result createResult() {
		return new VideoResult();
	}
}
